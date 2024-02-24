package com.minin.dtos.messages;

import lombok.Data;

import java.util.List;

@Data
public class MessageDto {

    private String senderUsername;
    private String receiverUsername;
    private String title;
    private String message;
    private List<String> files;

}
