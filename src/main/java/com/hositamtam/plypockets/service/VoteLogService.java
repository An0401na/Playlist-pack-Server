package com.hositamtam.plypockets.service;

import com.hositamtam.plypockets.domain.Content;
import com.hositamtam.plypockets.domain.User;
import com.hositamtam.plypockets.domain.Vote;
import com.hositamtam.plypockets.dto.request.VoteRequestDto;
import com.hositamtam.plypockets.dto.response.VoteLogResponseDto;
import com.hositamtam.plypockets.dto.response.VoteResultResponseDto;
import com.hositamtam.plypockets.global.exception.custom.content.ContentNotFoundException;
import com.hositamtam.plypockets.global.exception.custom.user.UserNotFoundException;
import com.hositamtam.plypockets.global.exception.custom.vote.UserAlreadyVotedException;
import com.hositamtam.plypockets.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class VoteLogService {

    private final VoteLogRepository voteLogRepository;
    private final VoteLogQueryRepository voteLogQueryRepository;
    private final VoteRepository voteRepository;
    private final ContentRepository contentRepository;
    private final UserRepository userRepository;


    public VoteLogResponseDto checkUserVoteStatus(Long contentId, Long userId) {

        Integer choice = voteLogQueryRepository.findChoiceByUserIdAndVoteContentId(contentId, userId);

        if(choice == -1){
            return VoteLogResponseDto.of(false, null, null, null);
        }

        List<VoteResultResponseDto> voteResultResponseDtoList = getVoteResult(contentId);

        return VoteLogResponseDto.of(true, choice, getTotalVoteCnt(contentId), voteResultResponseDtoList);
    }


    public List<VoteResultResponseDto> getVoteDetils(Long contentId) {
        return getVoteResult(contentId);
    }

    private List<VoteResultResponseDto> getVoteResult(Long contentId) {

        List<Vote> votes = getVoteList(contentId);

        long totalVoteCnt = getTotalVoteCnt(contentId);

        List<VoteResultResponseDto> voteResultResponseDtoList = getVoteResultResponseDtos(votes, totalVoteCnt);

        return voteResultResponseDtoList;
    }

    private List<Vote> getVoteList(Long contentId) {
        List<Vote> votes = voteRepository.findVotesByContent_ContentIdOrderByChoiceAsc(contentId);
        return votes;
    }

    private long getTotalVoteCnt(Long contentId){
        Content content = contentRepository.findById(contentId)
                .orElseThrow(ContentNotFoundException::new);

        return content.getTotalVoteCnt();
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


    public VoteLogResponseDto submitVote(VoteRequestDto voteRequestDto) {
        Integer choice = -1;

        if(voteRequestDto.userId() != null){
            choice = voteLogQueryRepository.findChoiceByUserIdAndVoteContentId(voteRequestDto.contentId(), voteRequestDto.userId());
        }

        if (choice != -1){
            throw new UserAlreadyVotedException();
        }

        Long totalVoteCnt = registerVote(voteRequestDto);

        List<VoteResultResponseDto> voteResultResponseDtoList = getVoteResult(voteRequestDto.contentId());

        return VoteLogResponseDto.of(true, voteRequestDto.choice(), totalVoteCnt, voteResultResponseDtoList);
    }

    private Long registerVote(VoteRequestDto voteRequestDto) {

        Content content = updateCotentTotalVoteCnt(voteRequestDto);

        Vote vote = updateVoteVoteCnt(voteRequestDto, content);

        User user = null;
        if(voteRequestDto.userId() != null){
            user = userRepository.findById(voteRequestDto.userId())
                    .orElseThrow(UserNotFoundException::new);
        }

        voteLogRepository.save(voteRequestDto.toEntity(vote, user));
        return content.getTotalVoteCnt();
    }

    private Vote updateVoteVoteCnt(VoteRequestDto voteRequestDto, Content content) {
        Vote vote = voteRepository.findByChoiceAndContent(voteRequestDto.choice(), content);

        vote.updateVoteCnt();
        return vote;
    }

    private Content updateCotentTotalVoteCnt(VoteRequestDto voteRequestDto) {
        Content content = contentRepository.findById(voteRequestDto.contentId())
                .orElseThrow(ContentNotFoundException::new);

        content.updateTotalVoteCnt();
        return content;
    }
}