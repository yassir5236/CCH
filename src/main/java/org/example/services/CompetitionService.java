package org.example.services;

import org.example.dtos.CompetitionDTO;
import org.example.dtos.GenaralResultDTO;
import org.example.entity.Competition;
import org.example.entity.Cyclist;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompetitionService {
    List<CompetitionDTO> getAllCompetitions();

    Optional<CompetitionDTO> getCompetition(UUID id);

    CompetitionDTO saveCompetition(CompetitionDTO CompetitionDTO);

     CompetitionDTO updateCompetition(UUID id, CompetitionDTO competitionDTO);

    void deleteCompetition(UUID id);

    void registerCyclistToCompetition(GenaralResultDTO resultDTO);

    void removeCyclistFromCompetition(UUID cyclistId, UUID competitionId);

    List<Cyclist> getCyclistsByCompetition(UUID competitionId);

}
