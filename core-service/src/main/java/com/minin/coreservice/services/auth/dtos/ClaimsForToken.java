package com.minin.coreservice.services.auth.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ClaimsForToken {
    private UUID identityId;
    private String email;
    private boolean isVerified;
}
