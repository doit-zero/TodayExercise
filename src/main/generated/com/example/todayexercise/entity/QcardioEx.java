package com.example.todayexercise.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QcardioEx is a Querydsl query type for cardioEx
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QcardioEx extends EntityPathBase<cardioEx> {

    private static final long serialVersionUID = -701071058L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QcardioEx cardioEx = new QcardioEx("cardioEx");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath exName = createString("exName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final NumberPath<Integer> km = createNumber("km", Integer.class);

    public final QWorkout workoutId;

    public QcardioEx(String variable) {
        this(cardioEx.class, forVariable(variable), INITS);
    }

    public QcardioEx(Path<? extends cardioEx> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QcardioEx(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QcardioEx(PathMetadata metadata, PathInits inits) {
        this(cardioEx.class, metadata, inits);
    }

    public QcardioEx(Class<? extends cardioEx> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.workoutId = inits.isInitialized("workoutId") ? new QWorkout(forProperty("workoutId"), inits.get("workoutId")) : null;
    }

}

