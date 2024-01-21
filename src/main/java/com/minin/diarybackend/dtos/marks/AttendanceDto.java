package com.minin.diarybackend.dtos.marks;

import lombok.Data;

@Data
public class AttendanceDto {
    private String subjectName;
    private int dueToIllness;
    private int respectful;
    private int wasAbsent;

    public AttendanceDto(int dueToIllness, int respectful, int wasAbsent) {
        this.dueToIllness = dueToIllness;
        this.respectful = respectful;
        this.wasAbsent = wasAbsent;
    }
}
