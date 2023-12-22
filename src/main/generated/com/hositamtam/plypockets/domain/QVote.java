package com.hositamtam.plypockets.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVote is a Querydsl query type for Vote
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVote extends EntityPathBase<Vote> {

    private static final long serialVersionUID = 953457162L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVote vote = new QVote("vote");

    public final com.hositamtam.plypockets.global.domain.QBaseEntity _super = new com.hositamtam.plypockets.global.domain.QBaseEntity(this);

    public final NumberPath<Integer> choice = createNumber("choice", Integer.class);

    public final QContent content;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final NumberPath<Long> voteCnt = createNumber("voteCnt", Long.class);

    public final NumberPath<Long> voteId = createNumber("voteId", Long.class);

    public QVote(String variable) {
        this(Vote.class, forVariable(variable), INITS);
    }

    public QVote(Path<? extends Vote> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVote(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVote(PathMetadata metadata, PathInits inits) {
        this(Vote.class, metadata, inits);
    }

    public QVote(Class<? extends Vote> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.content = inits.isInitialized("content") ? new QContent(forProperty("content")) : null;
    }

}

