package com.example.todayexercise.repository.StrengthEx;

import com.example.todayexercise.entity.User;
import com.querydsl.core.Tuple;

import java.time.LocalDate;
import java.util.List;

public interface StrengthExRepositoryCustom {
    List<Tuple> getStrengthEx(User user, LocalDate localDate);
}
