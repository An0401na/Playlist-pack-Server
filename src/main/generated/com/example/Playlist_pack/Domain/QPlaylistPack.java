package com.example.Playlist_pack.Domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlaylistPack is a Querydsl query type for PlaylistPack
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlaylistPack extends EntityPathBase<PlaylistPack> {

    private static final long serialVersionUID = 539640476L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlaylistPack playlistPack = new QPlaylistPack("playlistPack");

    public final NumberPath<Long> playlistpackId = createNumber("playlistpackId", Long.class);

    public final ListPath<Playlist, QPlaylist> playlists = this.<Playlist, QPlaylist>createList("playlists", Playlist.class, QPlaylist.class, PathInits.DIRECT2);

    public final QUser user;

    public QPlaylistPack(String variable) {
        this(PlaylistPack.class, forVariable(variable), INITS);
    }

    public QPlaylistPack(Path<? extends PlaylistPack> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlaylistPack(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlaylistPack(PathMetadata metadata, PathInits inits) {
        this(PlaylistPack.class, metadata, inits);
    }

    public QPlaylistPack(Class<? extends PlaylistPack> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

