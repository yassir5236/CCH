package org.example.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.embeddeds.ResultKey;
import org.example.entity.Cyclist;
import org.example.entity.Stage;

import java.time.Duration;
import java.util.UUID;

public record ResultDTO(

        @NotNull(message = "cyclistID is required")
        UUID cyclistID,

        @NotNull(message = "stageId is required")
        UUID stageId,

        @NotNull(message = "time is required")
        Duration time,

        @NotNull(message = "rank is required")
        int rank
) {
}
