package com.example.todayexercise.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWorkout is a Querydsl query type for Workout
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWorkout extends EntityPathBase<Workout> {

    private static final long serialVersionUID = -296228808L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWorkout workout = new QWorkout("workout");

    public final StringPath cardioExTime = createString("cardioExTime");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final StringPath strengthExTime = createString("strengthExTime");

    public final QUser usersId;

    public QWorkout(String variable) {
        this(Workout.class, forVariable(variable), INITS);
    }

    public QWorkout(Path<? extends Workout> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWorkout(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWorkout(PathMetadata metadata, PathInits inits) {
        this(Workout.class, metadata, inits);
    }

    public QWorkout(Class<? extends Workout> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.usersId = inits.isInitialized("usersId") ? new QUser(forProperty("usersId")) : null;
    }

}

