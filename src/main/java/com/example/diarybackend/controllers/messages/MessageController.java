package com.example.diarybackend.controllers.messages;

import com.example.diarybackend.config.security.custom.CustomPrincipal;
import com.example.diarybackend.controllers.messages.requests.MessageRequest;
import com.example.diarybackend.dtos.MessageDto;
import com.example.diarybackend.models.Message;
import com.example.diarybackend.services.messages.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/message")
public class MessageController {

    private final IMessageService messageService;

    @GetMapping("sent")
    public List<MessageDto> findSentMessages(CustomPrincipal principal) {
        return messageService.findSentMessages(principal.getIdentityId());
    }

    @GetMapping("received")
    public List<MessageDto> findReceivedMessages(CustomPrincipal principal) {
        return messageService.findReceivedMessages(principal.getIdentityId());
    }

    @PostMapping("new")
    public Message createNewMessage (CustomPrincipal principal, @RequestBody MessageRequest messageRequest) {
        messageRequest.setSenderId(principal.getIdentityId());
        return messageService.create(messageRequest);
    }

}
