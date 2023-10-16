package com.example.diarybackend.services.marks;

import com.example.diarybackend.controllers.marks.requests.MarkCreateRequest;
import com.example.diarybackend.mappers.MarkMapper;
import com.example.diarybackend.models.Mark;
import com.example.diarybackend.repositories.MarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MarkService implements IMarkService {

    private final MarkRepository markRepository;
    private final MarkMapper markMapper;

    @Override
    public Mark create(MarkCreateRequest markCreateRequest) {

        Mark mark = markMapper.requestToEntity(markCreateRequest);

        return markRepository.save(mark);
    }

    @Override
    public List<Mark> findAllMarksByStudentId(UUID studentId) {
        return markRepository.findMarksByStudentId(studentId);
    }

    @Override
    public List<Mark> findAllMarksByStudentIdAndSubjectId(UUID studentId, UUID subjectId) {
        return markRepository.findMarksByStudentIdAndSubjectId(studentId, subjectId);
    }

}
