package com.example.todayexercise.entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
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

    @Builder
    public Workout(String cardioExTime, String strengthExTime, LocalDateTime createdAt, Boolean isDeleted, User usersId) {
        this.cardioExTime = cardioExTime;
        this.strengthExTime = strengthExTime;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.usersId = usersId;
    }

}
