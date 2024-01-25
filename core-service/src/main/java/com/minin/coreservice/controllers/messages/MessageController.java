package com.minin.coreservice.controllers.messages;

import com.minin.coreservice.controllers.messages.requests.MessageRequest;
import com.minin.dtos.MessageDto;
import com.minin.coreservice.services.messages.IMessageService;
import com.minin.custom.CustomPrincipal;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Messages")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/messages")
public class MessageController {

    private final IMessageService messageService;

    @GetMapping("sent")
    public List<MessageDto> findSentMessages(
            Authentication authentication,
            @RequestParam(defaultValue = "1", name = "p") int pageIndex
    ) {
        CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
        return messageService.findSentMessages(
                principal.getIdentityId(),
                pageIndex - 1,
                20
        );
    }

    @GetMapping("received")
    public List<MessageDto> findReceivedMessages(
            Authentication authentication,
            @RequestParam(defaultValue = "1", name = "p") int pageIndex
    ) {
        CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
        return messageService.findReceivedMessages(
                principal.getIdentityId(),
                pageIndex - 1,
                20
        );
    }

    @PostMapping("new")
    public MessageDto createNewMessage (
            Authentication authentication,
            @RequestBody MessageRequest messageRequest
    ) {
        CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
        messageRequest.setSenderId(principal.getIdentityId());
        return messageService.create(messageRequest);
    }

}
