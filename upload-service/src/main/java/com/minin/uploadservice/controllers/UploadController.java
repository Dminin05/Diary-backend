package com.minin.uploadservice.controllers;

import com.minin.uploadservice.controllers.request.MessageIdRequest;
import com.minin.uploadservice.services.IUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/upload")
public class UploadController {

    private final IUploadService uploadService;

    @PostMapping
    public void uploadFiles(@RequestPart MessageIdRequest messageIdRequest, @RequestPart List<MultipartFile> files) {
        uploadService.uploadFiles(messageIdRequest.getMessageId(), files);
    }

}
