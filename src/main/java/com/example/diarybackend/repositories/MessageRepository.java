package com.example.diarybackend.repositories;

import com.example.diarybackend.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    Page<Message> findMessagesBySenderId(UUID senderId, Pageable pageable);

    Page<Message> findMessagesByReceiverId(UUID receiverId, Pageable pageable);

}
