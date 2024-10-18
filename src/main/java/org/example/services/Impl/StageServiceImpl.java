package org.example.services.Impl;

import org.example.entity.Stage;
import org.example.repository.StageRepository;
import org.example.services.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

@Service
public class StageServiceImpl implements StageService {


    private final StageRepository stageRepository;

    @Autowired
    public StageServiceImpl(StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }


    @Override
    public List<Stage> getAllStage() {
        return stageRepository.findAll();
    }

    @Override
    public Stage createStage(Stage stage) {
        return stageRepository.save(stage);
    }

    @Override
    public Optional<Stage> getStageById(UUID id) {
        return stageRepository.findById(id);
    }

    @Override
    public void updateStage(Stage stage) {
        stageRepository.save(stage);
    }

    @Override
    public void deleteStage(UUID id) {
        stageRepository.deleteById(id);
    }
}
