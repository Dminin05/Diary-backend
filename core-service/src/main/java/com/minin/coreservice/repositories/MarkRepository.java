package com.minin.coreservice.repositories;

import com.minin.coreservice.models.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MarkRepository extends JpaRepository<Mark, UUID> {

    List<Mark> findMarksByStudentId(UUID studentId);

}
