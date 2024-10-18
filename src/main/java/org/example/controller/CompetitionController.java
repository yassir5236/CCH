//package org.example.controller;
//
//
//import org.example.entity.Competition;
//import org.example.services.Impl.CompetitionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/competitions")
//public class CompetitionController {
//
//    @Autowired
//    private CompetitionService competitionService;
//
//    @PostMapping("/add")
//    public ResponseEntity<Competition> addCompetition(@RequestBody Competition competition) {
//        Competition savedCompetition = competitionService.saveCompetition(competition);
//        return new ResponseEntity<>(savedCompetition, HttpStatus.CREATED);
//    }
//}
