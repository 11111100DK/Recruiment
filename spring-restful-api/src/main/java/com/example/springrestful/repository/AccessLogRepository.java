package com.example.springrestful.repository;

import com.example.springrestful.model.entity.AccessLog.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AccessLogRepository extends JpaRepository<AccessLog, Integer> {
    AccessLog findByDate(Date date);
    List<AccessLog> findByDateBetween(Date startDate,Date endDate);
}