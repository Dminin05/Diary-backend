package com.example.diarybackend.services.subjects;

import com.example.diarybackend.dtos.SubjectDto;
import com.example.diarybackend.exceptions.ResourceNotFoundException;
import com.example.diarybackend.mappers.SubjectMapper;
import com.example.diarybackend.models.Subject;
import com.example.diarybackend.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubjectsService implements ISubjectsService{

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public Subject findById(UUID id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("subject_not_found"));
    }

    @Override
    public Subject create(SubjectDto subjectDto) {
        Subject subject = subjectMapper.dtoToEntity(subjectDto);
        return subjectRepository.save(subject);
    }

}
