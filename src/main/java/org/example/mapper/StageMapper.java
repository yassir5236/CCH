package org.example.mapper;


import org.example.dtos.ResultDTO;
import org.example.dtos.StageDTO;
import org.example.entity.Result;
import org.example.entity.Stage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StageMapper {
    StageDTO toDto (Stage stage);
    Stage toEntity(StageDTO stageDTO);
}