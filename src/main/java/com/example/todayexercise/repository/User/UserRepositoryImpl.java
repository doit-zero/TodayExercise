package com.example.todayexercise.repository.User;


import com.example.todayexercise.entity.QUser;
import com.example.todayexercise.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;


public class UserRepositoryImpl implements UserRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    QUser user = new QUser("user");

    @Override
    public User findByEmail(String email) {
        return queryFactory
                .selectFrom(user)
                .where(user.email.eq(email))
                .fetchFirst();
    }
}
