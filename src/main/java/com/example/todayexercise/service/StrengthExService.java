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
        List<Tuple> getAllStrengthExList = strengthExRepository.getAllStrengthEx(user);
        return getAllStrengthExList.toString();
    }
}
