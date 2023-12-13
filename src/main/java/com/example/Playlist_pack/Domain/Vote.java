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
public class Vote extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @Column(nullable = false)
    private Integer choice;

    @Column(nullable = false)
    private Long voteCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    @Builder
    public Vote(
            Long voteId,
            Integer choice,
            Long voteCnt,
            Content content
    ) {
        this.voteId = voteId;
        this.choice = choice;
        this.voteCnt = voteCnt;
        this.content = content;
    }

    public void updateVoteCnt() {
        voteCnt++;
    }
}
