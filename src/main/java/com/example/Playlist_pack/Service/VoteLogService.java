package com.example.Playlist_pack.Service;

import com.example.Playlist_pack.Domain.Vote;
import com.example.Playlist_pack.Dto.response.VoteLogResponseDto;
import com.example.Playlist_pack.Dto.response.VoteResultResponseDto;
import com.example.Playlist_pack.Repository.VoteLogQueryRepository;
import com.example.Playlist_pack.Repository.VoteLogRepository;
import com.example.Playlist_pack.Repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class VoteLogService {

    private final VoteLogRepository voteLogRepository;
    private final VoteLogQueryRepository voteLogQueryRepository;
    private final VoteRepository voteRepository;


    public VoteLogResponseDto getVoteDetils(Long contentId, Long userId) {

        Integer choice = voteLogQueryRepository.findChoiceByUserIdAndVoteContentId(contentId, userId);

        if(choice == -1){
            return VoteLogResponseDto.of(false, null, null);
        }

        List<VoteResultResponseDto> voteResultResponseDtoList = getVoteResult(contentId);

        return VoteLogResponseDto.of(true, choice, voteResultResponseDtoList);
    }

    private List<VoteResultResponseDto> getVoteResult(Long contentId) {
        List<Vote> votes = getVoteList(contentId);

        long totalVoteCnt = getTotalVoteCnt(votes);

        List<VoteResultResponseDto> voteResultResponseDtoList = getVoteResultResponseDtos(votes, totalVoteCnt);

        return voteResultResponseDtoList;
    }

    private static long getTotalVoteCnt(List<Vote> votes) {
        long totalVoteCnt = votes.stream()
                .mapToLong(Vote::getVoteCnt)
                .sum();
        return totalVoteCnt;
    }

    private List<Vote> getVoteList(Long contentId) {
        List<Vote> votes = voteRepository.findVotesByContent_ContentIdOrderByChoiceAsc(contentId);
        return votes;
    }

    private static List<VoteResultResponseDto> getVoteResultResponseDtos(List<Vote> votes, long totalVoteCnt) {
        List<VoteResultResponseDto> voteResultResponseDtoList = new ArrayList<>();
        for (Vote vote : votes) {
            int percent = getPercent(totalVoteCnt, vote);
            voteResultResponseDtoList.add(VoteResultResponseDto.of(vote.getChoice(), percent));
        }
        return voteResultResponseDtoList;
    }

    private static int getPercent(long totalVoteCnt, Vote vote) {
        int percent = (int) Math.round(((double) vote.getVoteCnt() / totalVoteCnt)*100);
        return percent;
    }


}