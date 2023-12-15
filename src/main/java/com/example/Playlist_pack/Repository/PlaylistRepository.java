package com.example.Playlist_pack.Repository;

import com.example.Playlist_pack.Domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    Optional<Playlist> findByPlaylistIdAndUserId(Long userId, Long playlistId);
    List<Playlist> findAllByUser_UserId(Long userId);

}
