package org.example.controller;

import org.example.dtos.CyclistDTO;
import org.example.entity.Cyclist;
import org.example.services.CyclistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cyclists")
public class CyclistController {
    private final CyclistService cyclistService;

    public CyclistController(CyclistService cyclistService) {
        this.cyclistService= cyclistService;
    }

    @GetMapping
    public ResponseEntity<List<CyclistDTO>> findAll() {
        List<CyclistDTO> cyclists = cyclistService.getCyclists();
        return new ResponseEntity<>(cyclists, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CyclistDTO> addCyclist(@RequestBody CyclistDTO cyclistDTO) {
        CyclistDTO newCyclist = cyclistService.saveCyclist(cyclistDTO);
        return new ResponseEntity<>(newCyclist, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CyclistDTO>  getCyclist(@PathVariable UUID id){
        CyclistDTO cyclistDTO = cyclistService.getCyclistById(id);
        return new ResponseEntity<>(cyclistDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CyclistDTO> deleteCyclist(@PathVariable UUID id){
        cyclistService.deleteCyclist(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CyclistDTO> updateCyclist(@PathVariable UUID id, @RequestBody CyclistDTO cyclistDTO) {
        CyclistDTO updatedCyclist = cyclistService.updateCyclist(id, cyclistDTO);
        return new ResponseEntity<>(updatedCyclist, HttpStatus.OK);
    }


}
