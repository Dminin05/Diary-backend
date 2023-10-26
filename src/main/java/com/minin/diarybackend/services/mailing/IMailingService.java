package com.minin.diarybackend.services.mailing;

import com.minin.diarybackend.controllers.mailing.requests.EmailSendRequest;

import java.util.UUID;

public interface IMailingService {

    void sendVerificationCode(UUID identityId);

    void sendVerificationCode(EmailSendRequest emailSendRequest);

}
