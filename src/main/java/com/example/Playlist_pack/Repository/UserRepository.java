package com.example.Playlist_pack.Repository;

import com.example.Playlist_pack.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);

    Optional<User> findByPassword(String password);
}
