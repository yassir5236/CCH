//package org.example.services.Impl;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//import org.example.embeddeds.GeneralResultKey;
//import org.example.entity.Competition;
//import org.example.entity.Cyclist;
//import org.example.entity.GeneralResult;
//import org.example.repository.CompetitionRepository;
//import org.example.repository.CyclistRepository;
//import org.example.repository.GeneralResultRepository;
//import org.example.services.Impl.CompetitionServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.Duration;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class CompetitionServiceImplTest {
//
//    @Mock
//    private CompetitionRepository competitionRepository;
//
//    @Mock
//    private CyclistRepository cyclistRepository;
//
//    @Mock
//    private GeneralResultRepository generalResultRepository;
//
//    @InjectMocks
//    private CompetitionServiceImpl competitionService;
//
//
//    private UUID cyclistId;
//    private UUID competitionId;
//    private Cyclist cyclist;
//    private Competition competition;
//    private GeneralResult generalResult;
//    private GeneralResult result1;
//    private GeneralResult result2;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        cyclistId = UUID.randomUUID();
//        competitionId = UUID.randomUUID();
//
//        GeneralResultKey key = new GeneralResultKey(cyclistId, competitionId);
//        generalResult = new GeneralResult();
//        generalResult.setId(key);
//
//
//        cyclist = new Cyclist();
//        cyclist.setId(cyclistId);
//        cyclist.setFirstName("John");
//        cyclist.setLastName("Doe");
//
//        competition = new Competition();
//        competition.setId(competitionId);
//        competition.setName("Tour de France");
//    }
//
//    @Test
//    void testSaveCompetition() {
//        when(competitionRepository.save(competition)).thenReturn(competition);
//
//        Competition savedCompetition = competitionService.saveCompetition(competition);
//
//        assertNotNull(savedCompetition);
//        assertEquals(competition.getId(), savedCompetition.getId());
//        verify(competitionRepository, times(1)).save(competition);
//    }
//
//    @Test
//    void testUpdateCompetition() {
//        // Simule la mise à jour
//        competition.setName("Updated Competition");
//        when(competitionRepository.save(competition)).thenReturn(competition);
//
//        competitionService.updateCompetition(competition);
//
//        verify(competitionRepository, times(1)).save(competition);
//        assertEquals("Updated Competition", competition.getName());
//    }
//
//    @Test
//    void testGetCompetition() {
//        when(competitionRepository.findById(competitionId)).thenReturn(Optional.of(competition));
//
//        Optional<Competition> foundCompetition = competitionService.getCompetition(competitionId);
//
//        assertTrue(foundCompetition.isPresent());
//        assertEquals(competition.getId(), foundCompetition.get().getId());
//        verify(competitionRepository, times(1)).findById(competitionId);
//    }
//
//    @Test
//    void testGetAllCompetitions() {
//        List<Competition> competitions = Arrays.asList(competition);
//        when(competitionRepository.findAll()).thenReturn(competitions);
//
//        List<Competition> allCompetitions = competitionService.getAllCompetitions();
//
//        assertNotNull(allCompetitions);
//        assertEquals(1, allCompetitions.size());
//        verify(competitionRepository, times(1)).findAll();
//    }
//
//    @Test
//    void testDeleteCompetition() {
//        doNothing().when(competitionRepository).deleteById(competitionId);
//
//        competitionService.deleteCompetition(competitionId);
//
//        verify(competitionRepository, times(1)).deleteById(competitionId);
//    }
//
//
//
//
//
//    @Test
//    void testRegisterCyclistToCompetition() {
//        when(cyclistRepository.findById(cyclistId)).thenReturn(Optional.of(cyclist));
//        when(competitionRepository.findById(competitionId)).thenReturn(Optional.of(competition));
//
//        competitionService.registerCyclistToCompetition(cyclistId, competitionId, 1, Duration.ofHours(4));
//
//        verify(generalResultRepository, times(1)).save(any(GeneralResult.class));
//    }
//
//    @Test
//    void testRegisterCyclistToCompetition_CyclistNotFound() {
//        when(cyclistRepository.findById(cyclistId)).thenReturn(Optional.empty());
//
//        try {
//            competitionService.registerCyclistToCompetition(cyclistId, competitionId, 1, Duration.ofHours(4));
//        } catch (IllegalArgumentException e) {
//            assertEquals("Cyclist or Competition not found", e.getMessage());
//        }
//
//        verify(generalResultRepository, never()).save(any(GeneralResult.class));
//    }
//
//    @Test
//    void testRegisterCyclistToCompetition_CompetitionNotFound() {
//        when(competitionRepository.findById(competitionId)).thenReturn(Optional.empty());
//
//        try {
//            competitionService.registerCyclistToCompetition(cyclistId, competitionId, 1, Duration.ofHours(4));
//        } catch (IllegalArgumentException e) {
//            assertEquals("Cyclist or Competition not found", e.getMessage());
//        }
//
//        verify(generalResultRepository, never()).save(any(GeneralResult.class));
//    }
//
//
//
//
//
//    @Test
//    void testRemoveCyclistFromCompetition_CyclistNotFound() {
//        when(generalResultRepository.findById(generalResult.getId())).thenReturn(Optional.empty());
//
//        try {
//            competitionService.removeCyclistFromCompetition(cyclistId, competitionId);
//        } catch (IllegalArgumentException e) {
//            assertEquals("The cyclist is not registered for this competition", e.getMessage());
//        }
//
//        verify(generalResultRepository, never()).delete(any());
//    }
//
//
//
//    @Test
//    public void testGetCyclistsByCompetition_NoResults() {
//        // Simuler le comportement du repository avec une liste vide
//        when(generalResultRepository.findByCompetitionId(competitionId))
//                .thenReturn(Collections.emptyList());
//
//        // Appeler la méthode que nous testons
//        List<Cyclist> cyclists = competitionService.getCyclistsByCompetition(competitionId);
//
//        // Vérifier que la liste est vide
//        assertEquals(0, cyclists.size());
//    }
//}
//
//
//
