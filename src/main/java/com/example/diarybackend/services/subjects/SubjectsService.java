package com.example.diarybackend.services.subjects;

import com.example.diarybackend.dtos.SubjectDto;
import com.example.diarybackend.mappers.SubjectMapper;
import com.example.diarybackend.models.Subject;
import com.example.diarybackend.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectsService implements ISubjectsService{

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public Subject create(SubjectDto subjectDto) {
        Subject subject = subjectMapper.dtoToEntity(subjectDto);
        return subjectRepository.save(subject);
    }

}
