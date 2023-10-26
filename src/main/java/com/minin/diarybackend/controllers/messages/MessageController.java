package com.minin.diarybackend.controllers.messages;

import com.minin.diarybackend.config.security.custom.CustomPrincipal;
import com.minin.diarybackend.controllers.messages.requests.MessageRequest;
import com.minin.diarybackend.dtos.MessageDto;
import com.minin.diarybackend.services.messages.IMessageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Messages")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/messages")
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
    public MessageDto createNewMessage (CustomPrincipal principal, @RequestBody MessageRequest messageRequest) {
        messageRequest.setSenderId(principal.getIdentityId());
        return messageService.create(messageRequest);
    }

}
