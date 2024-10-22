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

import org.example.entity.Cyclist;
import org.example.repository.CyclistRepository;
import org.example.services.CyclistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class CyclistService {

    @Autowired
    private CyclistRepository cyclistRepository;

    @Autowired
    private CyclistMapper cyclistMapper;  // Mapper injecté

    public CyclistDTO getCyclistById(Long id) {
        Cyclist cyclist = cyclistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cyclist not found"));
        return cyclistMapper.toDto(cyclist);  // Conversion avec MapStruct
    }

    public CyclistDTO createCyclist(CyclistDTO cyclistDTO) {
        Cyclist cyclist = cyclistMapper.toEntity(cyclistDTO);  // Conversion avec MapStruct
        return cyclistMapper.toDto(cyclistRepository.save(cyclist));
    }

    // Autres méthodes...
}
