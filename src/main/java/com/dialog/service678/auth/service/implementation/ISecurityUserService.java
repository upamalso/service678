package com.dialog.service678.auth.service.implementation;

public interface ISecurityUserService {

    String validatePasswordResetToken(long id, String token);

}
