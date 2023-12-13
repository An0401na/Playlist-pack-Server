package com.example.Playlist_pack.Repository;

import com.example.Playlist_pack.Domain.Content;
import com.example.Playlist_pack.Domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findVotesByContent_ContentIdOrderByChoiceAsc(Long content);
    Vote findByChoiceAndContent(Integer choice, Content content);
}
