package com.example.Playlist_pack.Domain;


import com.example.Playlist_pack.Global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Playlist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistId;

    @Column(nullable = false)
    private String spotifyId;

    //포장지 idx
    @Column(nullable = false)
    private String coverIdx;

    //장식 idx
    @Column(nullable = false)
    private String decoIdx;

    //컬러 idx
    @Column(nullable = false)
    private String colorIdx;

    @Column(nullable = false)
    private String friendname;

    @Column(nullable = false)
    private String letter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Playlist(
            Long playlistId,
            String spotifyId,
            String coverIdx,
            String decoIdx,
            String colorIdx,
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
