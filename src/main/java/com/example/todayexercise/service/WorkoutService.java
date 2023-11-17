package com.example.todayexercise.service;

import com.example.todayexercise.entity.StrengthEx;
import com.example.todayexercise.entity.User;
import com.example.todayexercise.entity.Workout;
import com.example.todayexercise.repository.StrengthEx.StrengthExRepository;
import com.example.todayexercise.repository.Workout.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class WorkoutService {

    private final StrengthExRepository strengthExRepository;
    private final WorkoutRepository workoutRepository;


    @Transactional
    public String recordStrengthEx(User user, Map<String, Object> workoutMap) {

        List<Map<String, Object>> strengthExList = (List<Map<String, Object>>) workoutMap.get("strengthEx");
        String workTime = workoutMap.get("workTime").toString();

        Workout workout = Workout.builder()
                .strengthExTime(workTime)
                .createdAt(LocalDateTime.now())
                .usersId(user)
                .build();
        workoutRepository.save(workout);

        for (Map<String, Object> strength : strengthExList) {
            StrengthEx strengthEx = StrengthEx.builder()
                    .part((String) strength.get("part"))
                    .exName((String) strength.get("exName"))
                    .kg((Integer) strength.get("kg"))
                    .rep((Integer) strength.get("rep"))
                    .set((Integer) strength.get("set"))
                    .workoutId(workout)
                    .build();
            strengthExRepository.save(strengthEx);
        }
    return "운동기록 저장 완료";
    }
}
