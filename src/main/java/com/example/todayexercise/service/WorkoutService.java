package com.example.todayexercise.service;

import com.example.todayexercise.entity.CardioEx;
import com.example.todayexercise.entity.StrengthEx;
import com.example.todayexercise.entity.User;
import com.example.todayexercise.entity.Workout;
import com.example.todayexercise.repository.CardioEx.CardioExRepository;
import com.example.todayexercise.repository.StrengthEx.StrengthExRepository;
import com.example.todayexercise.repository.Workout.WorkoutRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorkoutService {

    private final StrengthExRepository strengthExRepository;
    private final WorkoutRepository workoutRepository;
    private final CardioExRepository cardioExRepository;


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
                    .createdAt(LocalDateTime.now())
                    .build();
            strengthExRepository.save(strengthEx);
        }
    return "무산소 운동 기록 저장 완료";
    }

    @Transactional
    public String recordCardioEx(User user, Map<String, Object> workoutMap) {
        List<Map<String, Object>> cardioExList = (List<Map<String, Object>>) workoutMap.get("CardioEx");
        String workTime = workoutMap.get("workTime").toString();

        Workout workout = Workout.builder()
                .cardioExTime(workTime)
                .createdAt(LocalDateTime.now())
                .usersId(user)
                .build();
        workoutRepository.save(workout);

        for (Map<String, Object> cardio : cardioExList) {
            CardioEx cardioEx = CardioEx.builder()
                    .exName((String) cardio.get("exName"))
                    .km((Integer) cardio.get("km"))
                    .createdAt(LocalDateTime.now())
                    .workoutId(workout)
                    .build();

            cardioExRepository.save(cardioEx);
        }
        return "유산소 운동 기록 저장 완료";
    }


    public List<Map<String,Object>> getWorkoutList(User user) {
        List<Tuple> workoutList = workoutRepository.getWorkoutList(user);

        List<Map<String,Object>> resultList = new ArrayList<>();

        for (Tuple tuple: workoutList) {
            Map<String, Object> workoutMap = new HashMap<>();
            workoutMap.put("cardioExTime", tuple.get(0, String.class));
            workoutMap.put("strengthExTime", tuple.get(1, String.class));
            workoutMap.put("created_At", tuple.get(2, LocalDateTime.class));
            resultList.add(workoutMap);
        }


        return resultList;
    }
}
