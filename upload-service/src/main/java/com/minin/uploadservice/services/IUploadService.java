package com.minin.uploadservice.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IUploadService {

    void uploadFiles(
            UUID messageId,
            List<MultipartFile> files
    );

}
