package com.example.todayexercise.repository.CardioEx;

import com.example.todayexercise.entity.CardioEx;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardioExRepository extends JpaRepository<CardioEx,Long>,CardioExRepositoryCustom {
}
