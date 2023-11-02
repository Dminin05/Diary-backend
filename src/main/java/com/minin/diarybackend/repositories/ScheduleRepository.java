package com.minin.diarybackend.repositories;

import com.minin.diarybackend.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

    boolean existsByPairAndDateAndTeacher_Id(int pair, Date date, UUID teacherId);

}
