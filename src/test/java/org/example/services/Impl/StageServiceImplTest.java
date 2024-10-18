package org.example.services.Impl;


import org.example.entity.Competition;
import org.example.entity.Stage;
import org.example.enums.StageType;
import org.example.repository.StageRepository;
import org.example.services.Impl.StageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StageServiceImplTest {

    @Mock
    private StageRepository stageRepository;

    @InjectMocks
    private StageServiceImpl stageService;

    private Stage stage;
    private UUID stageId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        stageId = UUID.randomUUID();
        Competition competition = new Competition();
        competition.setId(UUID.randomUUID());

        stage = new Stage();
        stage.setId(stageId);
        stage.setNumber(1);
        stage.setStartLocation("Paris");
        stage.setEndLocation("Lyon");
        stage.setStartDateTime(LocalDateTime.now());
        stage.setStageType(StageType.HILLY_STAGE);
        stage.setCompetition(competition);
        stage.setResults(new HashSet<>());
    }

    @Test
    void testGetAllStage() {
        List<Stage> stages = Arrays.asList(stage);
        when(stageRepository.findAll()).thenReturn(stages);

        List<Stage> allStages = stageService.getAllStage();

        assertNotNull(allStages);
        assertEquals(1, allStages.size());
        assertEquals("Paris", allStages.get(0).getStartLocation());
        verify(stageRepository, times(1)).findAll();
    }

    @Test
    void testCreateStage() {
        when(stageRepository.save(stage)).thenReturn(stage);

        Stage createdStage = stageService.createStage(stage);

        assertNotNull(createdStage);
        assertEquals(stageId, createdStage.getId());
        assertEquals("Paris", createdStage.getStartLocation());
        assertEquals("Lyon", createdStage.getEndLocation());
        assertEquals(StageType.HILLY_STAGE, createdStage.getStageType());
        verify(stageRepository, times(1)).save(stage);
    }

    @Test
    void testGetStageById() {
        when(stageRepository.findById(stageId)).thenReturn(Optional.of(stage));

        Optional<Stage> foundStage = stageService.getStageById(stageId);

        assertTrue(foundStage.isPresent());
        assertEquals(stageId, foundStage.get().getId());
        verify(stageRepository, times(1)).findById(stageId);
    }

    @Test
    void testUpdateStage() {
        when(stageRepository.save(stage)).thenReturn(stage);

        stageService.updateStage(stage);

        verify(stageRepository, times(1)).save(stage);
    }

    @Test
    void testDeleteStage() {
        doNothing().when(stageRepository).deleteById(stageId);

        stageService.deleteStage(stageId);

        verify(stageRepository, times(1)).deleteById(stageId);
    }
}
