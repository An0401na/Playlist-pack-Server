package com.example.Playlist_pack.Domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
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
public class PlaylistPack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playlistpackId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Playlist와의 연관관계 설정, 1:N 연관관계의 주인으로 설정
    @OneToMany(mappedBy = "playlistPack", cascade = CascadeType.ALL)
    private List<Playlist> playlists = new ArrayList<>();
}
