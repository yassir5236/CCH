package org.example.services;

import org.example.entity.Cyclist;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CyclistService {
    List<Cyclist> getCyclists();

    Optional<Cyclist> getCyclistById(UUID id);

    Cyclist saveCyclist(Cyclist cyclist);

    void updateCyclist(Cyclist cyclist);

    void deleteCyclist(UUID id);
}
