package com.minin.diarybackend.services.mailing;

import java.util.UUID;

public interface IMailingService {

    void sendEmailVerificationCode(UUID identityId);

}
