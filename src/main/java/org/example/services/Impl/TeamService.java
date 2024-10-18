package org.example.services.Impl;


import org.example.entity.Team;
import org.example.repository.TeamRepository;
import org.example.services.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TeamService implements ITeamService {


    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> getAllTeam() {
        return teamRepository.findAll();
    }

//    @Override
    public Team getStatusById(UUID id) {
        return teamRepository.findById(id).orElse(null);
    }

    @Override
    public void createTeam(Team team) {
        teamRepository.save(team);
    }

}