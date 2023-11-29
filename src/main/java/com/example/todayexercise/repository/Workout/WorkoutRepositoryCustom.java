package com.example.todayexercise.repository.Workout;

import com.example.todayexercise.entity.User;
import com.querydsl.core.Tuple;

import java.util.List;

public interface WorkoutRepositoryCustom {
    List<Tuple> getWorkoutList(User user);
}
