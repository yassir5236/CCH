package org.example.controller;

import org.example.dtos.CompetitionDTO;
import org.example.entity.Competition;
import org.example.services.CompetitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/competitions")
public class CompetitionController {
    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService= competitionService;
    }

    @GetMapping
    public ResponseEntity<List<CompetitionDTO>> findAll() {
        List<CompetitionDTO> Competitions = competitionService.getAllCompetitions();
        return new ResponseEntity<>(Competitions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompetitionDTO> addCompetition(@RequestBody CompetitionDTO competitionDTO) {
        CompetitionDTO newCompetition = competitionService.saveCompetition(competitionDTO);
        return new ResponseEntity<>(newCompetition, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionDTO>  getCompetition(@PathVariable UUID id){
        Optional<CompetitionDTO> competitionDTO = competitionService.getCompetition(id);
        return new ResponseEntity<>(competitionDTO.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CompetitionDTO> deleteCompetition(@PathVariable UUID id){
        competitionService.deleteCompetition(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetitionDTO> updateCompetition(@PathVariable UUID id, @RequestBody CompetitionDTO competitionDTO) {
        CompetitionDTO updatedCompetition = competitionService.updateCompetition(id, competitionDTO);
        return new ResponseEntity<>(updatedCompetition, HttpStatus.OK);
    }


}
