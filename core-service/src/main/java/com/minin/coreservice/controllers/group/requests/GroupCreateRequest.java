package com.minin.coreservice.controllers.group.requests;

import lombok.Data;

@Data
public class GroupCreateRequest {
    private String title;
    private Integer year = 1;
}
