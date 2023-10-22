package com.minin.diarybackend.controllers.marks.requests;

import lombok.Data;

import java.util.UUID;

@Data
public class MarkCreateRequest {

    private UUID teacherId = null;
    private UUID studentId;
    private UUID subjectId;
    private String mark = "";

}
