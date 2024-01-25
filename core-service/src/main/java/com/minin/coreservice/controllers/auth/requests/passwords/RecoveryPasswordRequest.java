package com.minin.coreservice.controllers.auth.requests.passwords;

import lombok.Data;

@Data
public class RecoveryPasswordRequest {
    private String newPassword;
    private String confirmNewPassword;
}
