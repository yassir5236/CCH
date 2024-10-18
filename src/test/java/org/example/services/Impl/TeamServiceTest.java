package org.example.services.Impl;

import static org.junit.jupiter.api.Assertions.*;


import org.example.entity.Team;
import org.example.repository.TeamRepository;
import org.example.services.Impl.TeamService;
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

class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    private Team team;
    private UUID teamId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        teamId = UUID.randomUUID();
        team = new Team();
        team.setId(teamId);
        team.setName("Team A");
    }

    @Test
    void testGetAllTeam() {
        List<Team> teams = Arrays.asList(team);
        when(teamRepository.findAll()).thenReturn(teams);

        List<Team> allTeams = teamService.getAllTeam();

        assertNotNull(allTeams);
        assertEquals(1, allTeams.size());
        assertEquals("Team A", allTeams.get(0).getName());
        verify(teamRepository, times(1)).findAll();
    }

    @Test
    void testGetStatusById() {
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));

        Team foundTeam = teamService.getStatusById(teamId);

        assertNotNull(foundTeam);
        assertEquals(teamId, foundTeam.getId());
        assertEquals("Team A", foundTeam.getName());
        verify(teamRepository, times(1)).findById(teamId);
    }

    @Test
    void testCreateTeam() {
        when(teamRepository.save(team)).thenReturn(team);

        teamService.createTeam(team);

        verify(teamRepository, times(1)).save(team);
    }

    @Test
    void testGetTeamById() {
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));

        Optional<Team> foundTeam = teamService.getTeamById(teamId);

        assertTrue(foundTeam.isPresent());
        assertEquals(teamId, foundTeam.get().getId());
        verify(teamRepository, times(1)).findById(teamId);
    }
}
