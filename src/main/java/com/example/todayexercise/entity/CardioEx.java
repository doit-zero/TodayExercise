package com.example.todayexercise.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Entity
public class CardioEx {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx",nullable = false)
    private Long id;

    @Column(name = "exName")
    private String exName;

    @Column(name = "km")
    private Integer km;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "isDeleted", columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "workoutId")
    private Workout workoutId;

    @Builder

    public CardioEx(String exName, Integer km, LocalDateTime createdAt, Boolean isDeleted, Workout workoutId) {
        this.exName = exName;
        this.km = km;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.workoutId = workoutId;
    }
}
