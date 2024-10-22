package org.example.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TeamDTO(
        @NotBlank(message="le nom ne doit pas etre null")
        String name

) {
}
