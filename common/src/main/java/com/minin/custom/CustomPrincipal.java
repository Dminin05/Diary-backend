package com.minin.custom;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CustomPrincipal {
    private String username;
    private UUID identityId;
}
