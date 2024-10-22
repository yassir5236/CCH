package org.example.mapper;


import org.example.dtos.StageDTO;
import org.example.dtos.TeamDTO;
import org.example.entity.Stage;
import org.example.entity.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamDTO toDto (Team team);
    Team toEntity(TeamDTO teamDTO);
}