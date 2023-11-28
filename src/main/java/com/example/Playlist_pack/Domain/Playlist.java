package com.example.Playlist_pack.Domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
public class Playlist {
    //vus
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistId;

    private String coveridx;


    //포장지 idx
    private String decoIdx;
    //장식 idx
    private String colorIdx;
    //컬러 idx

    private String letter;
    private String friendname;


    // Song과의 연관관계 설정
    @OneToOne
    @JoinColumn(name = "song_id")
    private Song song;

    // PlaylistPack과의 연관관계 설정
    @ManyToOne
    @JoinColumn(name = "playlistpack_id")
    private PlaylistPack playlistPack;

}
