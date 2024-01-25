package com.minin.coreservice.services.mailing;

import com.minin.coreservice.controllers.mailing.requests.EmailSendRequest;

import java.util.UUID;

public interface IMailingService {

    void sendVerificationCode(UUID identityId);

    void sendVerificationCode(EmailSendRequest emailSendRequest);

}
