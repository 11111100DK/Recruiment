package com.example.springrestful.model.mapper;

import com.example.springrestful.model.entity.Candidate.InfoApply;
import com.example.springrestful.model.response.ResponseCandidate.ResponseInfoApply;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface InfoApplyMapper {
    ResponseInfoApply toResponseInfoApply(InfoApply infoApply);
    List<ResponseInfoApply> toResponseInfoApplyList(List<InfoApply> infoApplyList);
}
