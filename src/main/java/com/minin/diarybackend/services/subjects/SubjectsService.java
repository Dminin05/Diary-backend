package com.minin.diarybackend.services.subjects;

import com.minin.diarybackend.dtos.SubjectDto;
import com.minin.diarybackend.exceptions.ResourceNotFoundException;
import com.minin.diarybackend.mappers.SubjectMapper;
import com.minin.diarybackend.models.Subject;
import com.minin.diarybackend.repositories.SubjectRepository;
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
