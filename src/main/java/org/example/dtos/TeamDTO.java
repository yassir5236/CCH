package org.example.dtos;

import jakarta.validation.constraints.NotBlank;

public record TeamDTO(
        @NotBlank(message="le nom ne doit pas etre null")
        String name

) {
}
