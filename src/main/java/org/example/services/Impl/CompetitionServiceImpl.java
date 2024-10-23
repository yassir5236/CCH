//package org.example.services.Impl;
//
//import org.example.embeddeds.GeneralResultKey;
//import org.example.entity.Competition;
//import org.example.entity.Cyclist;
//import org.example.entity.GeneralResult;
//import org.example.repository.CompetitionRepository;
//import org.example.repository.CyclistRepository;
//import org.example.repository.GeneralResultRepository;
//import org.example.services.CompetitionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@Service
//public class CompetitionServiceImpl implements CompetitionService {
//
//    private final CompetitionRepository competitionRepository;
//    private final CyclistRepository cyclistRepository;
//    private final GeneralResultRepository generalResultRepository;
//
//    @Autowired
//    public CompetitionServiceImpl(CompetitionRepository competitionRepository,
//                                  CyclistRepository cyclistRepository,
//                                  GeneralResultRepository generalResultRepository) {
//        this.competitionRepository = competitionRepository;
//        this.cyclistRepository = cyclistRepository;
//        this.generalResultRepository = generalResultRepository;
//    }
//
//    @Override
//    public Competition saveCompetition(Competition competition) {
//        return competitionRepository.save(competition);
//    }
//
//    @Override
//    public void updateCompetition(Competition competition){
//        competitionRepository.save(competition);
//    }
//
//    @Override
//    public Optional<Competition> getCompetition(UUID id) {
//        return competitionRepository.findById(id);
//    }
//
//    @Override
//    public List<Competition> getAllCompetitions() {
//        return competitionRepository.findAll();
//    }
//
//    @Override
//    public void deleteCompetition(UUID id) {
//        competitionRepository.deleteById(id);
//    }
//
//
//
//    @Override
//    public void registerCyclistToCompetition(UUID cyclistId, UUID competitionId, int rank, Duration generalTime) {
//        // Récupérer le cycliste et la compétition
//        Optional<Cyclist> cyclistOpt = cyclistRepository.findById(cyclistId);
//        Optional<Competition> competitionOpt = competitionRepository.findById(competitionId);
//
//        if (cyclistOpt.isPresent() && competitionOpt.isPresent()) {
//            Cyclist cyclist = cyclistOpt.get();
//            Competition competition = competitionOpt.get();
//
//            // Créer une nouvelle entrée pour le GeneralResult
//                GeneralResultKey generalResultKey = new GeneralResultKey(cyclistId, competitionId);
//            GeneralResult generalResult = new GeneralResult();
//            generalResult.setId(generalResultKey);
//            generalResult.setCyclist(cyclist);
//            generalResult.setCompetition(competition);
//            generalResult.setGeneralRank(rank);
//            generalResult.setGeneralTime(generalTime);
//
//
//            generalResultRepository.save(generalResult);
//        } else {
//            throw new IllegalArgumentException("Cyclist or Competition not found");
//        }
//    }
//
//
//    @Override
//    public void removeCyclistFromCompetition(UUID cyclistId, UUID competitionId) {
//        GeneralResultKey key = new GeneralResultKey(cyclistId, competitionId);
//
//        // Vérifier si le coureur est inscrit à la compétition
//        Optional<GeneralResult> result = generalResultRepository.findById(key);
//        if (result.isPresent()) {
//            // Supprimer l'inscription
//            generalResultRepository.delete(result.get());
//        } else {
//            throw new IllegalArgumentException("The cyclist is not registered for this competition");
//        }
//    }
//
//
//    @Override
//    public List<Cyclist> getCyclistsByCompetition(UUID competitionId) {
//        // Vérification que les résultats retournés ne sont pas null
//        List<GeneralResult> results = generalResultRepository.findByCompetitionId(competitionId);
//
//        if (results == null || results.isEmpty()) {
//            return Collections.emptyList(); // Retourne une liste vide si aucun résultat
//        }
//
//        return results.stream()
//                .filter(result -> result.getCyclist() != null) // Vérifie que le cycliste n'est pas null
//                .map(GeneralResult::getCyclist)
//                .collect(Collectors.toList());
//    }
//}
//
//
//
//


package org.example.services.Impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.dtos.CompetitionDTO;
import org.example.dtos.GenaralResultDTO;
import org.example.embeddeds.GeneralResultKey;
import org.example.entity.Competition;
import org.example.entity.Cyclist;
import org.example.entity.GeneralResult;
import org.example.mapper.CompetitionMapper;
import org.example.mapper.CompetitionMapperImpl;
import org.example.repository.CompetitionRepository;
import org.example.repository.CyclistRepository;
import org.example.repository.GeneralResultRepository;
import org.example.services.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final CompetitionMapper competitionMapper;
    private final CyclistRepository cyclistRepository;
    private final GeneralResultRepository generalResultRepository;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository, CompetitionMapper competitionMapper, CyclistRepository cyclistRepository, GeneralResultRepository generalResultRepository) {
        this.competitionRepository = competitionRepository;
        this.competitionMapper = competitionMapper;
        this.cyclistRepository = cyclistRepository;
        this.generalResultRepository = generalResultRepository;

    }


    public List<CompetitionDTO> getAllCompetitions() {
        try {
            List<Competition> competitions = competitionRepository.findAll();
            return competitions.stream().map(competitionMapper::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    @Override
    public Optional<CompetitionDTO> getCompetition(UUID id) {
        Optional<Competition> competitionOpt = competitionRepository.findById(id);
        return competitionOpt.map(competitionMapper::toDto);
    }


    @Override
    public CompetitionDTO saveCompetition(CompetitionDTO competitionDTO) {
        competitionRepository.save(competitionMapper.toEntity(competitionDTO));
        return competitionDTO;
    }

    @Override
    @Transactional
    public void deleteCompetition(UUID id) {
        if (competitionRepository.existsById(id)) {
            competitionRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Competition not found for id: " + id);
        }
    }


    @Override
    public CompetitionDTO updateCompetition(UUID id, CompetitionDTO competitionDTO) {
        Optional<Competition> existingCompetitionOpt = competitionRepository.findById(id);

        if (existingCompetitionOpt.isPresent()) {
            Competition existingCompetition = existingCompetitionOpt.get();
            Competition updatedCompetition = competitionMapper.toEntity(competitionDTO);
            updatedCompetition.setId(existingCompetition.getId());
            competitionRepository.save(updatedCompetition);
            return competitionMapper.toDto(updatedCompetition);
        } else {
            throw new EntityNotFoundException("Competition not found for id: " + id);
        }
    }

    @Override
    @Transactional
    public void registerCyclistToCompetition(GenaralResultDTO resultDTO) {
        Optional<Competition> competitionOpt = competitionRepository.findById(resultDTO.competitionId());
        Optional<Cyclist> cyclistOpt = cyclistRepository.findById(resultDTO.cyclistId());

        if (competitionOpt.isPresent() && cyclistOpt.isPresent()) {
            GeneralResult generalResult = new GeneralResult();
            generalResult.setCyclist(cyclistOpt.get());
            generalResult.setCompetition(competitionOpt.get());
            generalResult.setGeneralTime(resultDTO.generalTime());
            generalResult.setGeneralRank(resultDTO.generalRank());

           generalResultRepository.save(generalResult);
        } else {
            throw new EntityNotFoundException("Cyclist or Competition not found");
        }
    }


    @Override
    @Transactional
    public void removeCyclistFromCompetition(UUID cyclistId, UUID competitionId) {
        GeneralResultKey key = new GeneralResultKey(cyclistId, competitionId);
        if (generalResultRepository.existsById(key)) {
            generalResultRepository.deleteById(key);
        } else {
            throw new EntityNotFoundException("General result for the specified cyclist and competition not found");
        }
    }



    @Override
    public List<Cyclist> getCyclistsByCompetition(UUID competitionId) {
        Optional<Competition> competitionOpt = competitionRepository.findById(competitionId);

        if (competitionOpt.isPresent()) {
            List<GeneralResult> generalResults = generalResultRepository.findByCompetitionId(competitionId);


            return generalResults.stream()
                    .map(GeneralResult::getCyclist)
                    .collect(Collectors.toList());
        } else {
            throw new EntityNotFoundException("Competition not found with ID: " + competitionId);
        }
    }



}
