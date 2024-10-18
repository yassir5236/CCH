package org.example.services;

import org.example.entity.Stage;
import org.example.entity.Team;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StageService {
    List<Stage> getAllStage();
    Stage  createStage(Stage stage);
    Optional<Stage> getStageById(UUID id);
    void updateStage(Stage stage);
    void deleteStage(UUID id);

}
