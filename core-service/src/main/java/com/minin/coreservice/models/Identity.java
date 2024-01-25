package com.minin.coreservice.models;

import com.minin.coreservice.models.types.IdentityType;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private IdentityType type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToOne(mappedBy = "identity")
    private Credentials credentials;

}
