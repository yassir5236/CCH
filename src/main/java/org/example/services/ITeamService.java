package org.example.services;

import org.example.entity.Team;

import java.util.List;
import java.util.UUID;

public interface ITeamService {
    List<Team> getAllTeam();
    Team getStatusById(UUID id);
    void createTeam(Team team);
}