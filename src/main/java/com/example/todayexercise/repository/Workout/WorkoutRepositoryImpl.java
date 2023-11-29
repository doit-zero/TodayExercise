package com.example.todayexercise.repository.Workout;
import com.example.todayexercise.entity.QWorkout;
import com.example.todayexercise.entity.User;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

public class WorkoutRepositoryImpl implements WorkoutRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public WorkoutRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    QWorkout workout = QWorkout.workout;

    @Override
    public List<Tuple> getWorkoutList(User user) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneWeekAgo = now.minusWeeks(1);

        return queryFactory
                .select(workout.cardioExTime,workout.strengthExTime,workout.createdAt)
                .from(workout)
                .where(
                        workout.usersId.eq(user),
                        workout.createdAt.between(oneWeekAgo,now)
                )
                .fetch();
    }


}
