package com.example.todayexercise.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx",nullable = false)
    private Long id;

    @Column(name = "cardioExTime")
    private String cardioExTime;

    @Column(name = "strengthExTime")
    private String strengthExTime;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "isDeleted", columnDefinition = "boolean default false")
    private Boolean isDeleted;
    @ManyToOne
    @JoinColumn(name = "usersId")
    private User usersId;

}
