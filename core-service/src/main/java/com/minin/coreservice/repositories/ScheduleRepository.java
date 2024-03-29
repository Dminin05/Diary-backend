package com.minin.coreservice.repositories;

import com.minin.coreservice.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

    boolean existsByPairAndDateAndTeacher_Id(int pair, Date date, UUID teacherId);

    List<Schedule> findAllByDateBetweenAndGroup_Title(Date dateFrom, Date dateTo, String groupTitle);

}
