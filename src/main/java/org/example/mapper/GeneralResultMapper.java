package org.example.mapper;


import org.example.dtos.CompetitionDTO;
import org.example.dtos.GenaralResultDTO;
import org.example.entity.Competition;
import org.example.entity.GeneralResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GeneralResultMapper {
    GenaralResultDTO toDto (GeneralResult generalResult);
    GeneralResult toEntity(GenaralResultDTO genaralResultDTO);
}