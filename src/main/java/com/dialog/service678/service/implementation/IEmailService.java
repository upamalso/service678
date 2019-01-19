package com.dialog.service678.service.implementation;

import com.dialog.service678.auth.event.OnRegistrationCompleteEvent;
import com.dialog.service678.entity.User;
import freemarker.template.TemplateException;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.IOException;

public interface IEmailService {

    ResponseEntity<?> constructResendVerificationTokenEmail(final OnRegistrationCompleteEvent event, final User user, final String token) throws IOException, TemplateException, MessagingException;

    ResponseEntity<?> constructResetPasswordTokenEmail(final OnRegistrationCompleteEvent event, final User user, final String token) throws IOException, TemplateException, MessagingException;

}

