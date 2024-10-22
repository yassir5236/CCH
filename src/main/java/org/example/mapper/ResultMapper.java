package org.example.mapper;


import org.example.dtos.GenaralResultDTO;
import org.example.dtos.ResultDTO;
import org.example.entity.GeneralResult;
import org.example.entity.Result;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResultMapper {
    ResultDTO toDto (Result result);
    Result toEntity(ResultDTO resultDTO);
}