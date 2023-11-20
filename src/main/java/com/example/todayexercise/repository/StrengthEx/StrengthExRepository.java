package com.example.todayexercise.repository.StrengthEx;

import com.example.todayexercise.entity.StrengthEx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrengthExRepository extends JpaRepository<StrengthEx,Long> ,StrengthExRepositoryCustom {
}
