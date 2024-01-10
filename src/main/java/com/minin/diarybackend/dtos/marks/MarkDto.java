package com.minin.diarybackend.dtos.marks;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MarkDto {
    private String subjectTitle;
    private List<MarkBaseInfo> marks;

}
