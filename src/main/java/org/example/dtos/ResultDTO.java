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

        @NotBlank(message = "cyclistID is required")
        UUID cyclistID,
        @NotBlank(message = "stageId is required")
        UUID stageId,
        @NotBlank(message = "stage is required")
        Stage stage,
        @NotBlank(message = "time is required")
        Duration time,
        @NotBlank(message = "rank is required")
        int rank
) {
}
