package com.minin.coreservice.controllers.auth.requests.passwords;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}
