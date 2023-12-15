package com.example.Playlist_pack.Repository;

import com.example.Playlist_pack.Domain.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    @Query("SELECT p FROM Playlist p JOIN p.user u WHERE u.id = :userId AND p.playlistId = :playlistId")
    Optional<Playlist> findByUserIdAndPlaylistId(@Param("userId") Long userId, @Param("playlistId") Long playlistId);
    List<Playlist> findAllByUser_UserId(Long userId);

}
