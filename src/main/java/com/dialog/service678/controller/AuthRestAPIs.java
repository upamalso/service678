package com.dialog.service678.controller;

import com.dialog.service678.auth.error.InvalidOldPasswordException;
import com.dialog.service678.auth.event.OnRegistrationCompleteEvent;
import com.dialog.service678.auth.service.implementation.ISecurityUserService;
import com.dialog.service678.dto.PasswordDto;
import com.dialog.service678.dto.UserDto;
import com.dialog.service678.dto.response.ApiResponse;
import com.dialog.service678.entity.User;
import com.dialog.service678.entity.VerificationToken;
import com.dialog.service678.message.request.LoginForm;
import com.dialog.service678.repository.RoleRepository;
import com.dialog.service678.repository.UserRepository;
import com.dialog.service678.service.implementation.IEmailService;
import com.dialog.service678.service.implementation.IUserService;
import freemarker.template.TemplateException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

import static com.dialog.service678.dto.response.ApiResponse.getApiResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

    // Define the log object for this class
    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private IUserService userService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    @Autowired
    private ISecurityUserService securityUserService;

    @Autowired
    private IEmailService iEmailService;


    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    /**
     * @Des User sign in
     * @Param LoginForm data
     * @Return resultcode/resultdescription in json format
     */
    @PostMapping(path = "/signin", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> authenticateUser(
            @Valid @RequestBody LoginForm loginRequest,
            final HttpServletRequest request
    ) {

        log.info("authenticateUser method started. ");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        ApiResponse apiResponse = getApiResponse("200", "auth.message.logged", messageSource, log);

        log.info(((User) authentication.getPrincipal()).getUserName() + " has been successfully logged");

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

    }

    /**
     * @Des Using a Spring Event to Create the Token and Send the Verification Email and save data
     * @Param UserDto data
     * @Param HttpServletRequest
     * @Return resultcode/resultdescription in json format
     */
    @PostMapping(path = "/signup", produces = "application/json")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody UserDto userDto,
            final HttpServletRequest request
    ) {
        log.info("registerUser method started. ");

        User user = userService.registerNewUserAccount(userDto);

        eventPublisher.publishEvent(new OnRegistrationCompleteEvent
                (user, getAppUrl(request)));

        ApiResponse apiResponse = getApiResponse("200", "auth.message.logged", messageSource, log);
        log.info("User " + user.getUserName() + " has been successfully registered");

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    /**
     * @Des The user will be redirected to an error page with the corresponding message if:
     * 1. The VerificationToken does not exist, for some reason or
     * 2. The VerificationToken has expired
     * <p>
     * There are two opportunities for improvement in handling the VerificationToken checking and expiration scenarios:
     * We can use a Cron Job to check for token expiration in the background
     * We can give the user the opportunity to get a new token once it has expired
     * <p>
     * The confirmRegistration controller will extract the value of the token parameter in the resulting GET
     * request and will use it to enable the User.
     * @Param HttpServletRequest
     * @Param token
     * @Return resultcode/resultdescription in json format
     **/

    @GetMapping(value = "/registration-confirm", produces = "application/json")
    public ResponseEntity<?> confirmRegistration(
            final HttpServletRequest request,
            @RequestParam("token") String token
    ) {

        log.info("confirmRegistration method started. ");

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {

            ApiResponse apiResponse = getApiResponse("404", "auth.message.invalidToken", messageSource, log);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }

        User user = verificationToken.getUser();

        Calendar cal = Calendar.getInstance();

        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {

            ApiResponse apiResponse = getApiResponse("200", "auth.message.expired", messageSource, log);
            return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

        }

        //If no errors are found, the user is enabled.
        user.setEnabled(true);

        log.info("User is enabled");

        userService.saveRegisteredUser(user);

        ApiResponse apiResponse = getApiResponse("200", "auth.message.accountVerified", messageSource, log);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

    }

    /**
     * @Des we’ll reset the existing token with a new expireDate. The, we’ll send the user a new email,with the new link/token
     * @Param token
     * @Param HttpServletRequest
     * @Return resultcode/resultdescription in json format
     ***/

    @GetMapping(value = "/resend-registration-token", produces = "application/json")
    public ResponseEntity<?> resendRegistrationToken(
            HttpServletRequest request,
            @RequestParam("token") String existingVerificationToken
    ) throws TemplateException, IOException, MessagingException {

        log.info("resendRegistrationToken method started. ");

        VerificationToken newToken = userService.generateNewVerificationToken(existingVerificationToken);

        User user = userService.getUser(newToken.getToken());

        OnRegistrationCompleteEvent event = new OnRegistrationCompleteEvent(user, getAppUrl(request));

        return iEmailService.constructResendVerificationTokenEmail(event ,user , newToken.getToken());

    }

    /**
     * @Des we’ll reset the password with a new token. The, we’ll send the user a new email, with the new link/token
     * @Param email
     * @Param HttpServletRequest
     * @Return resultcode/resultdescription in json format
     **/
    @GetMapping(value = "/reset-password", produces = "application/json")
    public ResponseEntity<?> resetPassword(
            HttpServletRequest request,
            @RequestParam("email") String userEmail
    ) throws TemplateException, IOException, MessagingException {
        log.info("resetPassword method started. ");

        User user = userService.findUserByEmail(userEmail);

        if (user == null) {
            ApiResponse apiResponse = getApiResponse("404", "auth.message.userNotFound", messageSource, log);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        OnRegistrationCompleteEvent event = new OnRegistrationCompleteEvent(user, getAppUrl(request));
        return iEmailService.constructResetPasswordTokenEmail(event ,user , token);

    }

    /**
     * @Des check the token is valid or not
     * @Param id
     * @Param token
     * @Return resultcode/resultdescription in json format
     **/
    @GetMapping(value = "/change-password", produces = "application/json")
    public ResponseEntity<?> showChangePasswordPage(
            @RequestParam("id") long id,
            @RequestParam("token") String token
    ) {

        log.info("showChangePasswordPage method started. ");

        String result = securityUserService.validatePasswordResetToken(id, token);
        if (result != null) {
            ApiResponse apiResponse = getApiResponse("404", "auth.message." + result, messageSource, log);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        //At this point, the user sees the simple Password Reset page – where the only
        //possible option is to provide a new password
        ApiResponse apiResponse = getApiResponse("200", "auth.message.validToken", messageSource, log);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    /**
     * @Des Notice how the method is secured via the @PreAuthorize annotation, since it should only accessible to logged in users.
     * @Param PasswordDto data
     * @Return resultcode/resultdescription in json format
     **/
    @PostMapping(value = "/update-password", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> changeUserPassword(
            @RequestBody PasswordDto passwordDto
    ) {

        log.info("changeUserPassword method started. ");

        final User user = userService.findUserByEmail(passwordDto.getEmail());

        if (!userService.checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
            throw new InvalidOldPasswordException();
        }
        userService.changeUserPassword(user, passwordDto.getNewPassword());

        ApiResponse apiResponse = getApiResponse("200", "auth.message.updatePasswordSuc", messageSource, log);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

    }

}
