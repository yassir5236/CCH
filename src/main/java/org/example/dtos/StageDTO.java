package org.example.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.enums.StageType;

import java.time.LocalDateTime;
import java.util.UUID;

public record StageDTO(
        @NotNull(message = "number of the stage os required.")
        int stageNumber,

        @NotBlank(message = "the Start location for the stage is required.")
        String startLocation,

        @NotBlank(message = "the end location for the stage is required.")
        String endLocation,

        @NotNull(message = "the start date for the stage is required.")
        LocalDateTime startDateTime,

        @NotNull(message = "stage type is required")
        StageType stageType,

        @NotNull(message = "the competition id for the stage is required.")
        UUID competitionId
) {
}
