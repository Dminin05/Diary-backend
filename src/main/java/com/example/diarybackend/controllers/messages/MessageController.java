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
    public List<MessageDto> findSentMessages(CustomPrincipal principal, @RequestParam(defaultValue = "1", name = "p") int pageIndex) {
        return messageService.findSentMessages(principal.getIdentityId(), pageIndex - 1, 20);
    }

    @GetMapping("received")
    public List<MessageDto> findReceivedMessages(CustomPrincipal principal, @RequestParam(defaultValue = "1", name = "p") int pageIndex) {
        return messageService.findReceivedMessages(principal.getIdentityId(), pageIndex - 1, 20);
    }

    @PostMapping("new")
    public Message createNewMessage (CustomPrincipal principal, @RequestBody MessageRequest messageRequest) {
        messageRequest.setSenderId(principal.getIdentityId());
        return messageService.create(messageRequest);
    }

}
