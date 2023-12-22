package com.hositamtam.plypockets.content;

import com.hositamtam.plypockets.domain.Content;

public enum ContentFixture {

    DEFAULT(1L, 100L, 100L, 10L);
    private Long contentId;

    private Long viewCnt;

    private Long likeCnt;

    private Long totalVoteCnt;

    ContentFixture(Long contentId,
                   Long viewCnt,
                   Long likeCnt,
                   Long totalVoteCnt) {
        this.contentId = contentId;
        this.viewCnt = viewCnt;
        this.likeCnt = likeCnt;
        this.totalVoteCnt = totalVoteCnt;
    }


    public Content getContent(){
        return Content.builder()
                .contentId(contentId)
                .viewCnt(viewCnt)
                .likeCnt(likeCnt)
                .totalVoteCnt(totalVoteCnt)
                .build();
    }

    public Content getContent(Long contentId,
                              Long viewCnt,
                              Long likeCnt,
                              Long totalVoteCnt){
        return Content.builder()
                .contentId(contentId)
                .viewCnt(viewCnt)
                .likeCnt(likeCnt)
                .totalVoteCnt(totalVoteCnt)
                .build();
    }
}
