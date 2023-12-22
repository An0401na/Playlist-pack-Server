package com.hositamtam.plypockets.repository;

import com.hositamtam.plypockets.domain.Playlist;
import com.hositamtam.plypockets.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    @Query("SELECT p FROM Playlist p JOIN p.user u WHERE u.nickname = :nickname AND p.playlistId = :playlistId")
    Optional<Playlist> findByUserNicknameAndPlaylistId(@Param("nickname") Long nickname, @Param("playlistId") Long playlistId);
    List<Playlist> findAllByUser(User user);

}
