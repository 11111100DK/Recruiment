package com.example.springrestful.model.mapper;

import com.example.springrestful.model.entity.Candidate.Candidate;
import com.example.springrestful.model.response.ResponseCandidate.ResponseCandidate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateMapper {
    ResponseCandidate toResponseCandidate(Candidate candidate);
    List<ResponseCandidate> toResponseCandidateList(List<Candidate> candidateList);
}
