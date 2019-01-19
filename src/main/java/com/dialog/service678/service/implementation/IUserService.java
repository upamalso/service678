package com.dialog.service678.service.implementation;

import com.dialog.service678.auth.error.EmailExistsException;
import com.dialog.service678.dto.UserDto;
import com.dialog.service678.entity.User;
import com.dialog.service678.entity.VerificationToken;

public interface IUserService {

    User registerNewUserAccount(UserDto userDto)
            throws EmailExistsException;

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    void saveRegisteredUser(User user);

    VerificationToken generateNewVerificationToken(String token);

    User getUser(String verificationToken);

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    boolean checkIfValidOldPassword(User user, String password);

    void changeUserPassword(User user, String password);

}
