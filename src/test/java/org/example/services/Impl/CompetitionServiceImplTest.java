package org.example.services.Impl;

import static org.junit.jupiter.api.Assertions.*;


import org.example.entity.Competition;
import org.example.repository.CompetitionRepository;
import org.example.services.Impl.CompetitionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompetitionServiceImplTest {

    @Mock
    private CompetitionRepository competitionRepository;

    @InjectMocks
    private CompetitionServiceImpl competitionService;

    private Competition competition;
    private UUID competitionId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        competitionId = UUID.randomUUID();
        competition = new Competition();
        competition.setId(competitionId);
        competition.setName("Test Competition");
    }

    @Test
    void testSaveCompetition() {
        when(competitionRepository.save(competition)).thenReturn(competition);

        Competition savedCompetition = competitionService.saveCompetition(competition);

        assertNotNull(savedCompetition);
        assertEquals(competition.getId(), savedCompetition.getId());
        verify(competitionRepository, times(1)).save(competition);
    }

    @Test
    void testUpdateCompetition() {
        // Simule la mise à jour
        competition.setName("Updated Competition");
        when(competitionRepository.save(competition)).thenReturn(competition);

        competitionService.updateCompetition(competition);

        verify(competitionRepository, times(1)).save(competition);
        assertEquals("Updated Competition", competition.getName());
    }

    @Test
    void testGetCompetition() {
        when(competitionRepository.findById(competitionId)).thenReturn(Optional.of(competition));

        Optional<Competition> foundCompetition = competitionService.getCompetition(competitionId);

        assertTrue(foundCompetition.isPresent());
        assertEquals(competition.getId(), foundCompetition.get().getId());
        verify(competitionRepository, times(1)).findById(competitionId);
    }

    @Test
    void testGetAllCompetitions() {
        List<Competition> competitions = Arrays.asList(competition);
        when(competitionRepository.findAll()).thenReturn(competitions);

        List<Competition> allCompetitions = competitionService.getAllCompetitions();

        assertNotNull(allCompetitions);
        assertEquals(1, allCompetitions.size());
        verify(competitionRepository, times(1)).findAll();
    }

    @Test
    void testDeleteCompetition() {
        doNothing().when(competitionRepository).deleteById(competitionId);

        competitionService.deleteCompetition(competitionId);

        verify(competitionRepository, times(1)).deleteById(competitionId);
    }
}



