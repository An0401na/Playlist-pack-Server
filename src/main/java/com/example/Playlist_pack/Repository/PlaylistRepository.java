package com.example.Playlist_pack.Repository;

import com.example.Playlist_pack.Domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    List<Playlist> findAllByUser_UserId(Long userId);
}
