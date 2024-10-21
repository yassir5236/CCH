package org.example.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.entity.GeneralResult;
import org.example.entity.Stage;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record CompetitionDTO(
        String name,
        LocalDate date,
        String place,
        double distance
) {
}