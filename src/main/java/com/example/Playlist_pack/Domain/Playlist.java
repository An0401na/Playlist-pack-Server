package com.example.Playlist_pack.Domain;


import com.example.Playlist_pack.Domain.enums.ColorType;
import com.example.Playlist_pack.Domain.enums.CoverType;
import com.example.Playlist_pack.Domain.enums.DecoType;
import com.example.Playlist_pack.Global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Playlist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistId;

    @Column(nullable = true)
    private String spotifyId;

    //포장지 idx
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CoverType coverIdx;

    //장식 idx
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DecoType decoIdx;

    //컬러 idx
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ColorType colorIdx;

    @Column(nullable = false)
    private String friendname;

    @Column(nullable = false, length = 300)
    private String letter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Playlist(
            Long playlistId,
            String spotifyId,
            CoverType coverIdx,
            DecoType decoIdx,
            ColorType colorIdx,
            String friendname,
            String letter,
            User user
    ){
        this.playlistId = playlistId;
        this.spotifyId = spotifyId;
        this.coverIdx = coverIdx;
        this.decoIdx = decoIdx;
        this.colorIdx = colorIdx;
        this.friendname = friendname;
        this.letter = letter;
        this.user = user;
    }



}
