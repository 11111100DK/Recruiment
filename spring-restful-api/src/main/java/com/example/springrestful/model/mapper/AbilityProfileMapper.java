package com.example.springrestful.model.mapper;

import com.example.springrestful.model.entity.Candidate.AbilityProfile;
import com.example.springrestful.model.response.ResponseCandidate.ResponseAbilityProfile;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface AbilityProfileMapper {
    ResponseAbilityProfile toResponseAbilityProfile(AbilityProfile abilityProfile);
    List<ResponseAbilityProfile> toResponseAbilityProfileList(List<AbilityProfile> abilityProfileList);
}
