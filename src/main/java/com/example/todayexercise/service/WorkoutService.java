package com.example.todayexercise.service;

import com.example.todayexercise.dto.request.CardioExDTO;
import com.example.todayexercise.dto.request.CardioExRequestDTO;
import com.example.todayexercise.dto.request.StrengthExDTO;
import com.example.todayexercise.dto.request.StrengthExRequestDTO;
import com.example.todayexercise.entity.CardioEx;
import com.example.todayexercise.entity.StrengthEx;
import com.example.todayexercise.entity.User;
import com.example.todayexercise.entity.Workout;
import com.example.todayexercise.exception.domain.User.UserErrorCode;
import com.example.todayexercise.exception.domain.User.UserException;
import com.example.todayexercise.repository.CardioEx.CardioExRepository;
import com.example.todayexercise.repository.StrengthEx.StrengthExRepository;
import com.example.todayexercise.repository.Workout.WorkoutRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
                .createdAt(LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime())
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
                    .createdAt(LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime())
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
                .createdAt(LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime())
                .usersId(user)
                .build();
        workoutRepository.save(workout);

        for (CardioExDTO cardio : cardioExList) {
            CardioEx cardioEx = CardioEx.builder()
                    .exName(cardio.getExName())
                    .km(cardio.getKm())
                    .createdAt(LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime())
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
            Map<String, Object> workoutMap = new HashMap<>(3);
            workoutMap.put("cardioExTime", tuple.get(0, String.class));
            workoutMap.put("strengthExTime", tuple.get(1, String.class));
            workoutMap.put("created_At", tuple.get(2, LocalDateTime.class));
            resultList.add(workoutMap);
        }

        return resultList;
    }

    @Transactional
    public List<Map<String,Object>> getAllWorkoutList(User user, Long cursor, int pageSize) {
        if(user == null) throw new UserException(UserErrorCode.NOT_EXIST_USER);

        PageRequest pageRequest = PageRequest.of(0,pageSize);

        Page<Object[]> workoutList = workoutRepository.findWorkoutWithCardioAndStrength(user,cursor,pageRequest);
        if(workoutList.isEmpty()) return null;

        List<Map<String,Object>> convertedList = new ArrayList<>();

        if(cursor == 0){
            Object[] firstObject = workoutList.getContent().get(0);
            cursor = (Long) firstObject[0] + 1;
        }

        long remainingCount = workoutRepository.countWorkoutsBeforeCursor(user,cursor);

        for (Object[] workout : workoutList) {
            Map<String, Object> resultMap = new LinkedHashMap<>();

            resultMap.put("workoutId", workout[0]);
            resultMap.put("createdAt", workout[1]);
            resultMap.put("cardioExTime", workout[2]);
            resultMap.put("caExName", workout[3]);
            resultMap.put("km", workout[4]);
            resultMap.put("strengthTime", workout[5]);
            resultMap.put("part", workout[6]);
            resultMap.put("stExName", workout[7]);
            resultMap.put("kg", workout[8]);
            resultMap.put("rep", workout[9]);
            resultMap.put("set", workout[10]);
            resultMap.put("remainingData", remainingCount == 1 ? false : true );

            convertedList.add(resultMap);

            if (remainingCount > 1) {
                --remainingCount;
            }

        }
        return convertedList;
    }
}
