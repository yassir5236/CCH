package org.example.services.Impl;

import org.example.embeddeds.GeneralResultKey;
import org.example.entity.Competition;
import org.example.entity.Cyclist;
import org.example.entity.GeneralResult;
import org.example.repository.CompetitionRepository;
import org.example.repository.CyclistRepository;
import org.example.repository.GeneralResultRepository;
import org.example.services.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final CyclistRepository cyclistRepository;
    private final GeneralResultRepository generalResultRepository;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository,
                                  CyclistRepository cyclistRepository,
                                  GeneralResultRepository generalResultRepository) {
        this.competitionRepository = competitionRepository;
        this.cyclistRepository = cyclistRepository;
        this.generalResultRepository = generalResultRepository;
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



    @Override
    public void registerCyclistToCompetition(UUID cyclistId, UUID competitionId, int rank, Duration generalTime) {
        // Récupérer le cycliste et la compétition
        Optional<Cyclist> cyclistOpt = cyclistRepository.findById(cyclistId);
        Optional<Competition> competitionOpt = competitionRepository.findById(competitionId);

        if (cyclistOpt.isPresent() && competitionOpt.isPresent()) {
            Cyclist cyclist = cyclistOpt.get();
            Competition competition = competitionOpt.get();

            // Créer une nouvelle entrée pour le GeneralResult
                GeneralResultKey generalResultKey = new GeneralResultKey(cyclistId, competitionId);
            GeneralResult generalResult = new GeneralResult();
            generalResult.setId(generalResultKey);
            generalResult.setCyclist(cyclist);
            generalResult.setCompetition(competition);
            generalResult.setGeneralRank(rank);
            generalResult.setGeneralTime(generalTime);


            generalResultRepository.save(generalResult);
        } else {
            throw new IllegalArgumentException("Cyclist or Competition not found");
        }
    }


    @Override
    public void removeCyclistFromCompetition(UUID cyclistId, UUID competitionId) {
        GeneralResultKey key = new GeneralResultKey(cyclistId, competitionId);

        // Vérifier si le coureur est inscrit à la compétition
        Optional<GeneralResult> result = generalResultRepository.findById(key);
        if (result.isPresent()) {
            // Supprimer l'inscription
            generalResultRepository.delete(result.get());
        } else {
            throw new IllegalArgumentException("The cyclist is not registered for this competition");
        }
    }


    @Override
    public List<Cyclist> getCyclistsByCompetition(UUID competitionId) {
        // Vérification que les résultats retournés ne sont pas null
        List<GeneralResult> results = generalResultRepository.findByCompetitionId(competitionId);

        if (results == null || results.isEmpty()) {
            return Collections.emptyList(); // Retourne une liste vide si aucun résultat
        }

        return results.stream()
                .filter(result -> result.getCyclist() != null) // Vérifie que le cycliste n'est pas null
                .map(GeneralResult::getCyclist)
                .collect(Collectors.toList());
    }
}

