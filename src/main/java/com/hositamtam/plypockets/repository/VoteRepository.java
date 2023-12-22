package com.hositamtam.plypockets.repository;

import com.hositamtam.plypockets.domain.Content;
import com.hositamtam.plypockets.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findVotesByContent_ContentIdOrderByChoiceAsc(Long content);
    Vote findByChoiceAndContent(Integer choice, Content content);
}
