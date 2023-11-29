package com.example.todayexercise.service;

import com.example.todayexercise.entity.User;
import com.example.todayexercise.repository.StrengthEx.StrengthExRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StrengthExService {

    private final StrengthExRepository strengthExRepository;

    public String getStrengthEx(User user, LocalDate localDate) {
        List<Tuple> getStrengthExList = strengthExRepository.getStrengthEx(user,localDate);
        return getStrengthExList.toString();
    }

    public String getAllStrengthEx(User user) {
        // TODO 커서 기반 페이지네이션 기능 구현해야함, 초기 7개 데이터 그 후에 20개씩 주고 더 이상 데이터가 없으면 false
        List<Tuple> getAllStrengthExList = strengthExRepository.getAllStrengthEx(user);
        return getAllStrengthExList.toString();
    }
}
