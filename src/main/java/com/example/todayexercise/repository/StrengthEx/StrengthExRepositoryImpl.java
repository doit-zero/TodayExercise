package com.example.todayexercise.repository.StrengthEx;

import com.example.todayexercise.entity.QStrengthEx;
import com.example.todayexercise.entity.User;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StrengthExRepositoryImpl implements StrengthExRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public StrengthExRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    QStrengthEx qStrengthEx = QStrengthEx.strengthEx;

    @Override
    public List<Tuple> getStrengthEx(User user, LocalDate localDate) {
        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(23,59,59);

        return queryFactory
                .select(qStrengthEx.exName,qStrengthEx.part,qStrengthEx.rep,qStrengthEx.set)
                .from(qStrengthEx)
                .where(qStrengthEx.userId.eq(user.getId())
                        .and(qStrengthEx.createdAt.between(startOfDay,endOfDay)))
                .fetchResults()
                .getResults();

    }

    @Override
    public List<Tuple> getAllStrengthEx(User user) {
       return queryFactory
                .select(qStrengthEx.exName,qStrengthEx.part,qStrengthEx.rep,qStrengthEx.set)
                .from(qStrengthEx)
                .where(qStrengthEx.userId.eq(user.getId()))
                .fetchResults()
                .getResults();
    }
}
