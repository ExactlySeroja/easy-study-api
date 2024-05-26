package com.seroja.easystudyapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "task_performance")
public class TaskPerformance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ed_material_id", nullable = false)
    private EducationalMaterial edMaterial;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "done_by", nullable = false)
    private AppUser doneBy;

    @NotNull
    @Column(name = "date_of_completion", nullable = false)
    private LocalDate dateOfCompletion;

    @NotNull
    @Column(name = "answer", nullable = false, length = Integer.MAX_VALUE)
    private String answer;

    @Column(name = "grade")
    private Integer grade;

}