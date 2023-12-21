package com.hositamtam.plypockets.repository;

import com.hositamtam.plypockets.domain.VoteLog;
import com.hositamtam.plypockets.global.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.hositamtam.plypockets.domain.QVoteLog.voteLog;

@Repository
@Transactional(readOnly = true)
public class VoteLogQueryRepository  extends QuerydslRepositorySupport {

    public VoteLogQueryRepository(){
        super(VoteLog.class);
    }


    //Todo : 성능 찍어두기
    //Todo : fetchJoin 공부해서 성능향상 시켜보기
    public Integer findChoiceByUserIdAndVoteContentId(Long contentId, Long userId){
        Integer result = select(voteLog.vote.choice)
                .from(voteLog)
                .join(voteLog.user)
                .join(voteLog.vote.content)
//                .fetchJoin()
                .where(
                        voteLog
                                .user
                                .userId
                                .eq(userId)
                                .and(voteLog
                                        .vote
                                        .content
                                        .contentId
                                        .eq(contentId))
                )
                .fetchOne();
        return result != null ? result : -1;
    }

}
