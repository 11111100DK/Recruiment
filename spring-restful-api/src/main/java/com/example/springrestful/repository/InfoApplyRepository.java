package com.example.springrestful.repository;

import com.example.springrestful.model.entity.Candidate.InfoApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface InfoApplyRepository extends JpaRepository<InfoApply, Integer> {
    InfoApply findById(int id);
    InfoApply findByCandidateId(int candidateId);
    InfoApply findByAppliedDate(Date appliedDate);

    InfoApply findByStatus(int status);


}
