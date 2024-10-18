package org.example.services.Impl;

import org.example.entity.Competition;
import org.example.repository.CompetitionRepository;
import org.example.services.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Competition saveCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    @Override
    public void updateCompetition(Competition competition){
        competitionRepository.save(competition);
    }

    @Override
    public Optional<Competition> getCompetition(UUID id) {
        return competitionRepository.findById(id);
    }

    @Override
    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    @Override
    public void deleteCompetition(UUID id) {
        competitionRepository.deleteById(id);
    }
}
