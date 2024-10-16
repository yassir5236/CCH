//package org.example.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.*;
//
//import java.io.Serializable;
//import java.time.Duration;
//
//@Entity
//@Table(name = "results")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class Result implements Serializable {
//
//    @EmbeddedId
//    private ResultKey id;  // Composite key for Result entity
//
//    @ManyToOne
//    @MapsId("cyclistId")  // Maps to cyclistId in ResultKey
//    @JoinColumn(name = "cyclist_id", nullable = false)  // Foreign key to Cyclist
//    private Cyclist cyclist;
//
//    @ManyToOne
//    @MapsId("stageId")  // Maps to stageId in ResultKey
//    @JoinColumn(name = "stage_id", nullable = false)  // Foreign key to Stage
//    private Stage stage;
//
//    @NotNull(message = "Time is required")
//    @Column(name = "time", nullable = false)
//    private Duration time;  // Duration of the result
//
//    @NotNull(message = "Rank is required")
//    @Column(name = "rank", nullable = false)
//    private int rank;  // Rank of the result
//}
