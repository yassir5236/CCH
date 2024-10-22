package org.example.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.embeddeds.GeneralResultKey;
import org.example.entity.Competition;
import org.example.entity.Cyclist;

import java.time.Duration;
import java.util.UUID;

public record GenaralResultDTO(

        @NotNull(message = "cyclistId is required")
        UUID cyclistId,

        @NotNull(message = "competitionId is required")
        UUID competitionId,

        @NotNull(message = "generalTime is required")
        Duration generalTime,

        @NotNull(message = "general rank is required")
        int generalRank

        ) {
}
