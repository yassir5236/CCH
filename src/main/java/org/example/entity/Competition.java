package org.example.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "competitions")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Competition implements Serializable {


    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id = UUID.randomUUID();

    @NotBlank(message = "Competition name is required")
    @Column(name = "competition_name", nullable = false)
    private String name;

    @NotNull(message = "Competition start_date is required")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull(message = "Competition end_date is required")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @NotBlank(message = "Place is required")
    @Column(name = "place", nullable = false)
    private String place;

    @NotNull(message = "Distance is required")
    @Column(name = "distance", nullable = false)
    private double distance;

    @OneToMany(mappedBy = "competition")
    Set<Stage> stages;

    @OneToMany(mappedBy = "competition")
    Set<GeneralResult> generalResults;
}