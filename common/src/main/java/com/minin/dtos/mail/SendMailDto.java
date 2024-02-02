package com.minin.dtos.mail;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMailDto {
    private String email;
    private String title;
    private String text;
}
