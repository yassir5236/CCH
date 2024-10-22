package org.example.mapper;


import org.example.dtos.CompetitionDTO;
import org.example.dtos.CyclistDTO;
import org.example.entity.Competition;
import org.example.entity.Cyclist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {
    CompetitionDTO toDto(Competition competition);
    Competition toEntity(CompetitionDTO competitionDTO);
}