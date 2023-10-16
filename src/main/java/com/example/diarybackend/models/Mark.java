package com.example.diarybackend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "marks")
@Data
@NoArgsConstructor
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "teacher_id")
    private UUID teacherId;

    @Column(name = "student_id")
    private UUID studentId;

    @Column(name = "subject_id")
    private UUID subjectId;

    @Column(name = "mark")
    private String mark;

}
