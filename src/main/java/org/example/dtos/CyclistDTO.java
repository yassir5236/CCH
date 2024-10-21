package org.example.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.entity.GeneralResult;
import org.example.entity.Result;
import org.example.entity.Team;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record CyclistDTO(

        @NotBlank(message = "first name is required")
        String firstName,
        @NotBlank(message = "last name is required")
        String lastName,

        @NotNull(message = "age is required")
        LocalDate age,

        @NotBlank(message = "nationality is required")
        String nationality,


        @JoinColumn(name = "team_id", nullable = false)
        UUID teamId


) {
}