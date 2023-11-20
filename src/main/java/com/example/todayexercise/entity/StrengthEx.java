package com.example.todayexercise.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class StrengthEx {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx",nullable = false)
    private Long id;

    @Column(name = "part")
    private String part;

    @Column(name = "exName")
    private String exName;

    @Column(name="kg")
    private Integer kg;

    @Column(name = "rep")
    private Integer rep;

    @Column(name = "sets")
    private Integer set;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "isDeleted", columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "workoutId")
    private Workout workoutId;

    @Column(name = "userId")
    private Long userId;

    @Builder
    public StrengthEx(String part, String exName, Integer kg, Integer rep, Integer set, LocalDateTime createdAt,Workout workoutId,Long userId) {
        this.part = part;
        this.exName = exName;
        this.kg = kg;
        this.rep = rep;
        this.set = set;
        this.createdAt = createdAt;
        this.workoutId = workoutId;
        this.userId = userId;

    }


    public StrengthEx() {
    }
}
