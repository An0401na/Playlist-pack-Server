package com.example.Playlist_pack.Service;

import com.example.Playlist_pack.Domain.Content;
import com.example.Playlist_pack.Domain.User;
import com.example.Playlist_pack.Domain.Vote;
import com.example.Playlist_pack.Dto.request.VoteRequestDto;
import com.example.Playlist_pack.Dto.response.VoteLogResponseDto;
import com.example.Playlist_pack.Dto.response.VoteResultResponseDto;
import com.example.Playlist_pack.Global.exception.custom.content.ContentNotFoundException;
import com.example.Playlist_pack.Global.exception.custom.user.UserNotFoundException;
import com.example.Playlist_pack.Global.exception.custom.vote.UserAlreadyVotedException;
import com.example.Playlist_pack.Repository.*;
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
            return VoteLogResponseDto.of(false, null, null);
        }

        List<VoteResultResponseDto> voteResultResponseDtoList = getVoteResult(contentId);

        return VoteLogResponseDto.of(true, choice, voteResultResponseDtoList);
    }



    public List<VoteResultResponseDto> getVoteDetils(Long contentId) {
        return getVoteResult(contentId);
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


    public VoteLogResponseDto submitVote(VoteRequestDto voteRequestDto) {

        Integer choice = voteLogQueryRepository.findChoiceByUserIdAndVoteContentId(voteRequestDto.contentId(), voteRequestDto.userId());

        if (choice != -1){
            throw new UserAlreadyVotedException();
        }

        registerVote(voteRequestDto);

        List<VoteResultResponseDto> voteResultResponseDtoList = getVoteResult(voteRequestDto.contentId());

        return VoteLogResponseDto.of(true, voteRequestDto.choice(), voteResultResponseDtoList);
    }

    private void registerVote(VoteRequestDto voteRequestDto) {

        Content content = updateCotentTotalVoteCnt(voteRequestDto);

        Vote vote = updateVoteVoteCnt(voteRequestDto, content);

        User user = userRepository.findById(voteRequestDto.userId())
                .orElseThrow(UserNotFoundException::new);

        voteLogRepository.save(voteRequestDto.toEntity(vote, user));
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