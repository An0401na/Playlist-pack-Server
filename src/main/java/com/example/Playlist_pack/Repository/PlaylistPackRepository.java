package com.example.Playlist_pack.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaylistPackRepository extends JpaRepository<PlaylistPack, Long> {
    Optional<PlaylistPack> findByUser_UserId(Long userId);
}
