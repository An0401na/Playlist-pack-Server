package com.example.Playlist_pack.Repository;

import com.example.Playlist_pack.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
