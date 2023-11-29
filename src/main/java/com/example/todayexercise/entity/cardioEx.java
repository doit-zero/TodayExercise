package com.example.todayexercise.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class cardioEx {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx",nullable = false)
    private Long id;

    @Column(name = "exName")
    private String exName;

    @Column(name = "km")
    private Short km;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "isDeleted", columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "workoutId")
    private Workout workoutId;

}