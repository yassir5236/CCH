package org.example.services;

import org.example.dtos.CyclistDTO;
import org.example.entity.Cyclist;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CyclistService {
    List<CyclistDTO> getCyclists();

    CyclistDTO getCyclistById(UUID id);

    CyclistDTO saveCyclist(CyclistDTO cyclistDTO);

    CyclistDTO updateCyclist(UUID id, CyclistDTO cyclistDTO) ;


    void deleteCyclist(UUID id);
}
