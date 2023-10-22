package com.minin.diarybackend.repositories;

import com.minin.diarybackend.models.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MarkRepository extends JpaRepository<Mark, UUID> {

    List<Mark> findMarksByStudentId(UUID studentId);

    List<Mark> findMarksByStudentIdAndSubjectId(UUID studentId, UUID subjectId);

}
