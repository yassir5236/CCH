package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.embeddeds.ResultKey;

import java.io.Serializable;
import java.time.Duration;

@Entity
@Table(name = "results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Result implements Serializable {

    @EmbeddedId
    private ResultKey id;

    @ManyToOne
    @MapsId("cyclistId")
    @JoinColumn(name = "cyclist_id", nullable = false)
    private Cyclist cyclist;

    @ManyToOne
    @MapsId("stageId")
    @JoinColumn(name = "stage_id", nullable = false)
    private Stage stage;

    @NotNull(message = "Time is required")
    @Column(name = "time", nullable = false)
    private Duration time;

    @NotNull(message = "Rank is required")
    @Column(name = "rank", nullable = false)
    private int rank;
}
