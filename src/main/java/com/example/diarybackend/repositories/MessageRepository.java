package com.example.diarybackend.repositories;

import com.example.diarybackend.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> findMessagesBySenderId(UUID senderId);

    List<Message> findMessagesByReceiverId(UUID receiverId);

}
