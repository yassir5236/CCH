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
        @NotBlank(message = "name is required ")
        String name,

        @NotNull(message = "date is required")
        LocalDate date,

        @NotBlank(message = "place is required")
        String place,

        @NotNull(message = "distance is required")
        double distance
) {
}