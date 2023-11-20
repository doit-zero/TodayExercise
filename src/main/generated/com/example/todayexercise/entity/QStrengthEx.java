package com.example.todayexercise.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStrengthEx is a Querydsl query type for StrengthEx
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStrengthEx extends EntityPathBase<StrengthEx> {

    private static final long serialVersionUID = 472579417L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStrengthEx strengthEx = new QStrengthEx("strengthEx");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath exName = createString("exName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final NumberPath<Integer> kg = createNumber("kg", Integer.class);

    public final StringPath part = createString("part");

    public final NumberPath<Integer> rep = createNumber("rep", Integer.class);

    public final NumberPath<Integer> set = createNumber("set", Integer.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final QWorkout workoutId;

    public QStrengthEx(String variable) {
        this(StrengthEx.class, forVariable(variable), INITS);
    }

    public QStrengthEx(Path<? extends StrengthEx> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStrengthEx(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStrengthEx(PathMetadata metadata, PathInits inits) {
        this(StrengthEx.class, metadata, inits);
    }

    public QStrengthEx(Class<? extends StrengthEx> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.workoutId = inits.isInitialized("workoutId") ? new QWorkout(forProperty("workoutId"), inits.get("workoutId")) : null;
    }

}

