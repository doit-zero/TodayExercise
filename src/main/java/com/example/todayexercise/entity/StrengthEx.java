package com.example.todayexercise.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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
    private Short kg;

    @Column(name = "rep")
    private Short rep;

    @Column(name = "sets")
    private Short set;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "isDeleted", columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "workoutId")
    private Workout workoutId;


}
