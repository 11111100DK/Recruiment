package com.example.springrestful.repository;

import com.example.springrestful.model.entity.Candidate.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    Candidate findCandidateById(int id);

    Candidate findByEmail(String email);
    List<Candidate> findByMajorContains(String major);
    List<Candidate> findByServiceContains(String service);
}
