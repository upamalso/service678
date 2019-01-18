package com.dialog.service678.service;

import com.dialog.service678.auth.error.EmailExistsException;
import com.dialog.service678.converter.UserConverter;
import com.dialog.service678.dto.UserDto;
import com.dialog.service678.entity.PasswordResetToken;
import com.dialog.service678.entity.User;
import com.dialog.service678.entity.VerificationToken;
import com.dialog.service678.repository.PasswordResetTokenRepository;
import com.dialog.service678.repository.RoleRepository;
import com.dialog.service678.repository.UserRepository;
import com.dialog.service678.repository.VerificationTokenRepository;
import com.dialog.service678.service.implementation.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;

    @Override
    public User registerNewUserAccount(UserDto userDto) throws EmailExistsException {
        if (emailExist(userDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email address:  " + userDto.getEmail()
            );
        }

        User user = userConverter.dtoToEntity(userDto);
        user.setName(userDto.getName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(false);
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));

        return userRepository.save(user);
    }

    //Checks for Duplicate Emails
    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    //Create verification token
    @Override
    public void createVerificationTokenForUser(final User user, final String token) {

        final VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(final String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public void saveRegisteredUser(final User user) {
        userRepository.save(user);
    }

    @Override
    public VerificationToken generateNewVerificationToken(final String token) {
        System.out.println("token >>"+token);
        VerificationToken vToken = tokenRepository.findByToken(token);
        System.out.println(">>>"+vToken.toString());
        vToken.updateToken(UUID.randomUUID()
                .toString());
        vToken = tokenRepository.save(vToken);
        return vToken;
    }

    @Override
    public User getUser(String verificationToken) {
        final VerificationToken token = tokenRepository.findByToken(verificationToken);
        if (token != null) {
            return token.getUser();
        }
        return null;
    }

    @Override
    public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    @Override
    public boolean checkIfValidOldPassword(final User user, final String password) {
        System.out.println("current pw>>>>"+user.getPassword());
        System.out.println("old password>>>"+password);
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public void changeUserPassword(final User user, final String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
