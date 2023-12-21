package com.hositamtam.plypockets.repository;

import com.hositamtam.plypockets.domain.VoteLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteLogRepository extends JpaRepository<VoteLog, Long> {
}

