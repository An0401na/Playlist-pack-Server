package com.hositamtam.plypockets.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QContent is a Querydsl query type for Content
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContent extends EntityPathBase<Content> {

    private static final long serialVersionUID = 2135890265L;

    public static final QContent content = new QContent("content");

    public final com.hositamtam.plypockets.global.domain.QBaseEntity _super = new com.hositamtam.plypockets.global.domain.QBaseEntity(this);

    public final NumberPath<Long> contentId = createNumber("contentId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> likeCnt = createNumber("likeCnt", Long.class);

    public final NumberPath<Long> totalVoteCnt = createNumber("totalVoteCnt", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final NumberPath<Long> viewCnt = createNumber("viewCnt", Long.class);

    public QContent(String variable) {
        super(Content.class, forVariable(variable));
    }

    public QContent(Path<? extends Content> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContent(PathMetadata metadata) {
        super(Content.class, metadata);
    }

}

