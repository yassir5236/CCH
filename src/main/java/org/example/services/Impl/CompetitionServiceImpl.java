package org.example.services.Impl;

import org.example.entity.Competition;
import org.example.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompetitionServiceImpl {

    private final CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    public Competition saveCompetition(Competition competition) {
        // Add validation logic if necessary
        return competitionRepository.save(competition);
    }

    public Optional<Competition> getCompetitionById(UUID id) {
        return competitionRepository.findById(id);
    }

    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    public void deleteCompetition(UUID id) {
        competitionRepository.deleteById(id);
    }
}
