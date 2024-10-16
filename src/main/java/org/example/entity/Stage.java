//package org.example.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import lombok.*;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//import java.util.Set;
//import java.util.UUID;
//
//@Entity
//@Table(name = "stages")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class Stage implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID id;
//
//    @NotNull(message = "Stage number is required")
//    @Column(name = "stage_number", nullable = false)
//    private int number;
//
//    @NotBlank(message = "Start location is required")
//    @Column(name = "start_location", nullable = false)
//    private String startLocation;
//
//    @NotBlank(message = "End location is required")
//    @Column(name = "end_location", nullable = false)
//    private String endLocation;
//
//    @NotNull(message = "Start date time is required")
//    @Column(name = "start_date_time", nullable = false)
//    private LocalDateTime startDateTime; // Fixed the casing for consistency
//
//    @NotNull(message = "Stage type is required")
//    @Enumerated(EnumType.STRING) // Assuming StageType is an Enum
//    @Column(name = "stage_type", nullable = false)
//    private StageType stageType;
//
//    @ManyToOne
//    @JoinColumn(name = "competition_id", nullable = false) // Set nullable to false for data integrity
//    private Competition competition;
//
//    @OneToMany(mappedBy = "stage", cascade = CascadeType.ALL, orphanRemoval = true) // Manage related results automatically
//    private Set<Result> results;
//}
