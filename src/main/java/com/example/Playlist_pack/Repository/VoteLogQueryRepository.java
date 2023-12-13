package com.example.Playlist_pack.Repository;

import com.example.Playlist_pack.Domain.VoteLog;
import com.example.Playlist_pack.Global.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.example.Playlist_pack.Domain.QVoteLog.voteLog;

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
