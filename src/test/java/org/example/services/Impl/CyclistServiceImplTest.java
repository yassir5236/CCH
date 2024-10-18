package org.example.services.Impl;

import static org.junit.jupiter.api.Assertions.*;


import org.example.entity.Cyclist;
import org.example.repository.CyclistRepository;
import org.example.services.Impl.CyclistServiceImpl;
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

class CyclistServiceImplTest {

    @Mock
    private CyclistRepository cyclistRepository;

    @InjectMocks
    private CyclistServiceImpl cyclistService;

    private Cyclist cyclist;
    private UUID cyclistId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cyclistId = UUID.randomUUID();
        cyclist = new Cyclist();
        cyclist.setId(cyclistId);
        cyclist.setFirstName("dsffd");
        cyclist.setLastName("vvvvv");
    }

    @Test
    void testSaveCyclist() {
        when(cyclistRepository.save(cyclist)).thenReturn(cyclist);

        Cyclist savedCyclist = cyclistService.saveCyclist(cyclist);

        assertNotNull(savedCyclist);
        assertEquals(cyclist.getId(), savedCyclist.getId());
        verify(cyclistRepository, times(1)).save(cyclist);
    }

    @Test
    void testSetUpValues() {
        // Vérification que le cycliste a bien été initialisé avec les bonnes valeurs
        assertNotNull(cyclist);
        assertEquals(cyclistId, cyclist.getId());
        assertEquals("dsffd", cyclist.getFirstName());
        assertEquals("vvvvv", cyclist.getLastName());
    }

    @Test
    void testDeleteCyclist() {
        doNothing().when(cyclistRepository).deleteById(cyclistId);

        cyclistService.deleteCyclist(cyclistId);

        verify(cyclistRepository, times(1)).deleteById(cyclistId);
    }

    @Test
    void testGetCyclistById() {
        when(cyclistRepository.findById(cyclistId)).thenReturn(Optional.of(cyclist));

        Optional<Cyclist> foundCyclist = cyclistService.getCyclistById(cyclistId);

        assertTrue(foundCyclist.isPresent());
        assertEquals(cyclist.getId(), foundCyclist.get().getId());
        verify(cyclistRepository, times(1)).findById(cyclistId);
    }

    @Test
    void testGetCyclists() {
        List<Cyclist> cyclists = Arrays.asList(cyclist);
        when(cyclistRepository.findAll()).thenReturn(cyclists);

        List<Cyclist> allCyclists = cyclistService.getCyclists();

        assertNotNull(allCyclists);
        assertEquals(1, allCyclists.size());
        verify(cyclistRepository, times(1)).findAll();
    }
}
