package com.minin.coreservice.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Identity sender;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private Identity receiver;

    @Column(name = "title")
    private String title;

    @Column(name = "message")
    private String message;

}
