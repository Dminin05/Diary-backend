package com.minin.diarybackend.controllers.marks.requests;

import lombok.Data;

import java.util.UUID;

@Data
public class MarkCreateRequest {

    private UUID identityId = null;
    private UUID studentId;
    private UUID scheduleId;
    private String mark = "";

}
