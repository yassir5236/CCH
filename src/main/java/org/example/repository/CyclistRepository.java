package org.example.repository;

import org.example.entity.Competition;
import org.example.entity.Cyclist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CyclistRepository extends JpaRepository<Cyclist, UUID> {
}
