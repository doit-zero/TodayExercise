package com.example.todayexercise.repository.User;

import com.example.todayexercise.entity.User;

public interface UserRepositoryCustom {
    User findByEmail(String email);
}
