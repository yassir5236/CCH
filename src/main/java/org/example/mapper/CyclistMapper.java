//package org.example.mapper;
//
//
//import org.example.dtos.CyclistDTO;
//import org.example.entity.Cyclist;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//@Mapper(componentModel = "spring")
//public interface CyclistMapper {
//
//    @Mapping(target = "teamId", source = "team.id")
//    CyclistDTO toDto(Cyclist cyclist);
//
//    @Mapping(target = "team", source = "teamId")
//    Cyclist toEntity(CyclistDTO cyclistDTO);
//
//}





package org.example.mapper;

import org.example.dtos.CyclistDTO;
import org.example.entity.Cyclist;
import org.example.entity.Team;
import org.example.services.Impl.TeamService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {TeamService.class})
public interface CyclistMapper {

    @Mapping(target = "teamId", source = "team.id")
    CyclistDTO toDto(Cyclist cyclist);

    @Mapping(target = "team", source = "teamId")
    Cyclist toEntity(CyclistDTO cyclistDTO);

    default Team map(UUID teamId, TeamService teamService) {
        if (teamId == null) {
            return null;
        }

        return teamService.getTeamById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found for ID: " + teamId));
    }
}
