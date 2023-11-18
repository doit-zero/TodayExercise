package com.example.todayexercise.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCardioEx is a Querydsl query type for CardioEx
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCardioEx extends EntityPathBase<CardioEx> {

    private static final long serialVersionUID = -636426930L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCardioEx cardioEx = new QCardioEx("cardioEx");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath exName = createString("exName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final NumberPath<Integer> km = createNumber("km", Integer.class);

    public final QWorkout workoutId;

    public QCardioEx(String variable) {
        this(CardioEx.class, forVariable(variable), INITS);
    }

    public QCardioEx(Path<? extends CardioEx> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCardioEx(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCardioEx(PathMetadata metadata, PathInits inits) {
        this(CardioEx.class, metadata, inits);
    }

    public QCardioEx(Class<? extends CardioEx> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.workoutId = inits.isInitialized("workoutId") ? new QWorkout(forProperty("workoutId"), inits.get("workoutId")) : null;
    }

}

