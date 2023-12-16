package com.example.springrestful.repository;

import com.example.springrestful.model.entity.Candidate.AbilityProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityProfileRepository extends JpaRepository<AbilityProfile, Integer> {
    AbilityProfile findById(int id);
    AbilityProfile findByTitle(String title);

}
