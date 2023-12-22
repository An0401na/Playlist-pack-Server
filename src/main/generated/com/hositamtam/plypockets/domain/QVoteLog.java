package com.hositamtam.plypockets.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVoteLog is a Querydsl query type for VoteLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVoteLog extends EntityPathBase<VoteLog> {

    private static final long serialVersionUID = 1823661274L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVoteLog voteLog = new QVoteLog("voteLog");

    public final com.hositamtam.plypockets.global.domain.QBaseEntity _super = new com.hositamtam.plypockets.global.domain.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final QUser user;

    public final QVote vote;

    public final NumberPath<Long> voteLogId = createNumber("voteLogId", Long.class);

    public QVoteLog(String variable) {
        this(VoteLog.class, forVariable(variable), INITS);
    }

    public QVoteLog(Path<? extends VoteLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVoteLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVoteLog(PathMetadata metadata, PathInits inits) {
        this(VoteLog.class, metadata, inits);
    }

    public QVoteLog(Class<? extends VoteLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
        this.vote = inits.isInitialized("vote") ? new QVote(forProperty("vote"), inits.get("vote")) : null;
    }

}

