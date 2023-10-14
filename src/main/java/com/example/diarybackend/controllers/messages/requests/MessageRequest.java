package com.example.diarybackend.controllers.messages.requests;

import lombok.Data;

import java.util.UUID;

@Data
public class MessageRequest {
    private UUID senderId = null;
    private UUID receiverId;
    private String title;
    private String message;
}
