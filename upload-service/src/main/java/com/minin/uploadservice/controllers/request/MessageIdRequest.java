package com.minin.uploadservice.controllers.request;

import lombok.Data;

import java.util.UUID;

@Data
public class MessageIdRequest {
    private UUID messageId;
}
