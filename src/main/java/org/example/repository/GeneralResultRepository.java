package org.example.repository;

import org.example.embeddeds.GeneralResultKey;
import org.example.entity.Competition;
import org.example.entity.GeneralResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GeneralResultRepository extends JpaRepository<GeneralResult, GeneralResultKey> {
    List<GeneralResult> findByCompetitionId(UUID competitionId);

}
