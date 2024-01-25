package com.minin.coreservice.services.subjects;

import com.minin.dtos.SubjectDto;
import com.minin.exceptions.ResourceNotFoundException;
import com.minin.coreservice.mappers.SubjectMapper;
import com.minin.coreservice.models.Subject;
import com.minin.coreservice.repositories.SubjectRepository;
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
