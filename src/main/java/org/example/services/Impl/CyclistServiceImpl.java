//package org.example.services.Impl;
//
//import org.example.entity.Cyclist;
//import org.example.repository.CyclistRepository;
//import org.example.services.CyclistService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class CyclistServiceImpl implements CyclistService {
//    private final CyclistRepository cyclistRepository;
//
//    @Autowired
//    public CyclistServiceImpl(CyclistRepository cyclistRepository) {
//        this.cyclistRepository = cyclistRepository;
//    }
//
//    @Override
//    public Cyclist saveCyclist(Cyclist cyclist) {
//        return cyclistRepository.save(cyclist);
//    }
//
//    @Override
//    public void updateCyclist(Cyclist cyclist) {
//        cyclistRepository.save(cyclist);
//    }
//
//    @Override
//    public void deleteCyclist(UUID id) {
//        cyclistRepository.deleteById(id);
//    }
//
//    @Override
//    public Optional<Cyclist> getCyclistById(UUID id) {
//        return cyclistRepository.findById(id);
//    }
//
//    @Override
//    public List<Cyclist> getCyclists() {
//        return cyclistRepository.findAll();
//    }
//}


package org.example.services.Impl;

import ch.qos.logback.core.testUtil.RandomUtil;
import jakarta.persistence.EntityNotFoundException;
import org.example.dtos.CyclistDTO;
import org.example.entity.Cyclist;
import org.example.entity.Team;
import org.example.mapper.CyclistMapper;
import org.example.repository.CyclistRepository;
import org.example.repository.TeamRepository;
import org.example.services.CyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class CyclistServiceImpl implements CyclistService {


    private final CyclistRepository cyclistRepository;
    private final TeamRepository teamRepository;
    private final  CyclistMapper cyclistMapper ;
    private final TeamService teamService;

    public CyclistServiceImpl(CyclistRepository cyclistRepository, TeamRepository teamRepository, CyclistMapper cyclistMapper , TeamService teamService) {
        this.cyclistRepository = cyclistRepository;
        this.teamRepository = teamRepository;
        this.cyclistMapper = cyclistMapper;
        this.teamService = teamService;

    }



    @Override
    public CyclistDTO getCyclistById(UUID id) {
        Optional<Cyclist> cyclist = cyclistRepository.findById(id);
        if (cyclist.isPresent()) {
            return cyclistMapper.toDto(cyclist.get());

        }
        throw new EntityNotFoundException("Cyclist not found with id: " + id);

    }


    @Override
    public CyclistDTO saveCyclist(CyclistDTO cyclistDTO) {
        Team team = teamRepository.findById(cyclistDTO.teamId())
                .orElseThrow(() -> new RuntimeException("this shit does not exist"));
        Cyclist cyclist = cyclistMapper.toEntity(cyclistDTO);
        cyclist.setTeam(team);
        return cyclistMapper.toDto(cyclistRepository.save(cyclist));
    }

    @Override
    public List<CyclistDTO> getCyclists() {
        List<Cyclist> cyclists = cyclistRepository.findAll();
        return cyclists.stream()
                .map(cyclistMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public CyclistDTO updateCyclist(UUID id, CyclistDTO cyclistDTO) {
        Cyclist existingCyclist = cyclistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cyclist not found with id: " + id));

        existingCyclist.setFirstName(cyclistDTO.firstName());
        existingCyclist.setLastName(cyclistDTO.lastName());
        existingCyclist.setNationality(cyclistDTO.nationality());
        existingCyclist.setAge(cyclistDTO.age());
        existingCyclist.setTeam(cyclistMapper.map(cyclistDTO.teamId(), teamService)); // Assurez-vous d'injecter TeamService ici si nécessaire

        // Sauvegarder les modifications
        return cyclistMapper.toDto(cyclistRepository.save(existingCyclist));
    }

    @Override
    public void deleteCyclist(UUID id) {
        cyclistRepository.deleteById(id);
    }


}
