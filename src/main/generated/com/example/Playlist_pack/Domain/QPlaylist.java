package com.example.Playlist_pack.Domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlaylist is a Querydsl query type for Playlist
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlaylist extends EntityPathBase<Playlist> {

    private static final long serialVersionUID = 2006455779L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlaylist playlist = new QPlaylist("playlist");

    public final com.example.Playlist_pack.Global.domain.QBaseEntity _super = new com.example.Playlist_pack.Global.domain.QBaseEntity(this);

    public final EnumPath<com.example.Playlist_pack.Domain.enums.ColorType> colorIdx = createEnum("colorIdx", com.example.Playlist_pack.Domain.enums.ColorType.class);

    public final EnumPath<com.example.Playlist_pack.Domain.enums.CoverType> coverIdx = createEnum("coverIdx", com.example.Playlist_pack.Domain.enums.CoverType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final EnumPath<com.example.Playlist_pack.Domain.enums.DecoType> decoIdx = createEnum("decoIdx", com.example.Playlist_pack.Domain.enums.DecoType.class);

    public final StringPath friendname = createString("friendname");

    public final StringPath letter = createString("letter");

    public final NumberPath<Long> playlistId = createNumber("playlistId", Long.class);

    public final StringPath spotifyId = createString("spotifyId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedDate = _super.updatedDate;

    public final QUser user;

    public QPlaylist(String variable) {
        this(Playlist.class, forVariable(variable), INITS);
    }

    public QPlaylist(Path<? extends Playlist> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlaylist(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlaylist(PathMetadata metadata, PathInits inits) {
        this(Playlist.class, metadata, inits);
    }

    public QPlaylist(Class<? extends Playlist> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

