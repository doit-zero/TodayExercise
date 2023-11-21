package com.example.todayexercise.service;

import com.example.todayexercise.dto.request.CardioExDTO;
import com.example.todayexercise.dto.request.CardioExRequestDTO;
import com.example.todayexercise.dto.request.StrengthExDTO;
import com.example.todayexercise.dto.request.StrengthExRequestDTO;
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
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class WorkoutService {

    private final StrengthExRepository strengthExRepository;
    private final WorkoutRepository workoutRepository;
    private final CardioExRepository cardioExRepository;


    @Transactional
    public String recordStrengthEx(User user, StrengthExRequestDTO workoutMap) {

        List<StrengthExDTO> strengthExList = workoutMap.getStrengthEx();
        String workTime = workoutMap.getWorkTime();

        Workout workout = Workout.builder()
                .strengthExTime(workTime)
                .createdAt(LocalDateTime.now())
                .usersId(user)
                .build();
        workoutRepository.save(workout);

        for (StrengthExDTO strength : strengthExList) {
            StrengthEx strengthEx = StrengthEx.builder()
                    .part(strength.getPart())
                    .exName(strength.getExName())
                    .kg(strength.getKg())
                    .rep(strength.getRep())
                    .set(strength.getSet())
                    .workoutId(workout)
                    .userId(user.getId())
                    .createdAt(LocalDateTime.now())
                    .build();
            strengthExRepository.save(strengthEx);
        }
    return "무산소 운동 기록 저장 완료";
    }

    @Transactional
    public String recordCardioEx(User user, CardioExRequestDTO workoutMap) {
        List<CardioExDTO> cardioExList = workoutMap.getCardioEx();
        String workTime = workoutMap.getWorkTime();

        Workout workout = Workout.builder()
                .cardioExTime(workTime)
                .createdAt(LocalDateTime.now())
                .usersId(user)
                .build();
        workoutRepository.save(workout);

        for (CardioExDTO cardio : cardioExList) {
            CardioEx cardioEx = CardioEx.builder()
                    .exName(cardio.getExName())
                    .km(cardio.getKm())
                    .createdAt(LocalDateTime.now())
                    .workoutId(workout)
                    .userId(user.getId())
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