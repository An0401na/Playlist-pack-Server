package com.example.Playlist_pack.Repository;

import com.example.Playlist_pack.Domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {}
