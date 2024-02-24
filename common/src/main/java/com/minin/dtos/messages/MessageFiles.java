package com.minin.dtos.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageFiles implements Serializable {
    private UUID messageId;
    private List<String> urls;
}
