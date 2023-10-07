package com.example.diarybackend.models;

import com.example.diarybackend.models.types.IdentityType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "identity")
@Data
@NoArgsConstructor
public class Identity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private IdentityType type;

    @Column(name = "student_id")
    private UUID studentId;

    @Column(name = "teacher_id")
    private UUID teacherId;

    @Column(name = "admin_id")
    private UUID adminId;

}
