package com.minin.diarybackend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "verification_codes")
@Data
@NoArgsConstructor
public class VerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "value")
    private String value;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(15);

}
