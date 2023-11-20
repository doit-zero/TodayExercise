package com.example.todayexercise.repository.Workout;

import com.example.todayexercise.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout,Long>,WorkoutRepositoryCustom {
}
