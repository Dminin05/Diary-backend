package com.example.diarybackend.dtos;

import lombok.Data;

@Data
public class MessageDto {

    private String senderUsername;
    private String receiverUsername;
    private String title;
    private String message;

}
