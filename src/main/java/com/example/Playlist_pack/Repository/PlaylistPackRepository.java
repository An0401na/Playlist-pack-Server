package com.example.Playlist_pack.Repository;


import com.example.Playlist_pack.Domain.PlaylistPack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaylistPackRepository extends JpaRepository<PlaylistPack, Long> {
    Optional<PlaylistPack> findByUserId(Long userId);
}
