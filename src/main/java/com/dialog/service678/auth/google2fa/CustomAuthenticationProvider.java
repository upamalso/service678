package com.dialog.service678.auth.google2fa;

import com.dialog.service678.entity.User;
import com.dialog.service678.repository.UserRepository;
import org.jboss.aerogear.security.otp.Totp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

//@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        final User user = userRepository.findByUserName(auth.getName());

        if ((user == null)) {
            System.out.println("Invalid username or password");
            throw new BadCredentialsException("Invalid username or password");
        }

        // to verify verification code
        if (user.getUsing2FA()) {
            final String verificationCode = ((CustomWebAuthenticationDetails) auth.getDetails()).getVerificationCode();
            final Totp totp = new Totp(user.getSecret());
            if (!isValidLong(verificationCode) || !totp.verify(verificationCode)) {
                throw new BadCredentialsException("Invalid verfication code");
            }

        }
        final Authentication result = super.authenticate(auth);
        return new UsernamePasswordAuthenticationToken(user, result.getCredentials(), result.getAuthorities());
    }

    private boolean isValidLong(String code) {
        try {
            Long.parseLong(code);
        } catch (final NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
