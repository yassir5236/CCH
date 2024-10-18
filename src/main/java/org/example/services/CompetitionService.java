package org.example.services;

import org.example.entity.Competition;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompetitionService {
    List<Competition> getAllCompetitions();

    Optional<Competition> getCompetition(UUID id);

    Competition saveCompetition(Competition competition);

    void updateCompetition(Competition competition);

    void deleteCompetition(UUID id);
}
