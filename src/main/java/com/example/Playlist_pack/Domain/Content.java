package com.example.Playlist_pack.Domain;

import com.example.Playlist_pack.Global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentId;

    @Column(nullable = false)
    private Long viewCnt;

    @Column(nullable = false)
    private Long likeCnt;

    @Column(nullable = false)
    private Long totalVoteCnt;

    @Builder
    public Content(
            Long contentId,
            Long viewCnt,
            Long likeCnt,
            Long totalVoteCnt
    ){
        this.contentId = contentId;
        this.viewCnt = viewCnt;
        this.likeCnt = likeCnt;
        this.totalVoteCnt = totalVoteCnt;
    }

    public void updateViewCnt() {
        this.viewCnt ++;
    }

    public void updateLikeCnt() {
        this.likeCnt ++;
    }
}
