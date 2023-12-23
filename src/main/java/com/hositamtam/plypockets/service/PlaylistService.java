
package com.hositamtam.plypockets.service;

import com.hositamtam.plypockets.domain.Playlist;
import com.hositamtam.plypockets.domain.User;
import com.hositamtam.plypockets.domain.enums.ColorType;
import com.hositamtam.plypockets.domain.enums.CoverType;
import com.hositamtam.plypockets.domain.enums.DecoType;
import com.hositamtam.plypockets.dto.request.PlaylistRegisterRequestDto;
import com.hositamtam.plypockets.global.exception.custom.user.UserNotFoundException;
import com.hositamtam.plypockets.repository.PlaylistRepository;
import com.hositamtam.plypockets.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    public void createPlaylist(PlaylistRegisterRequestDto playlistRegisterRequestDto, String nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(UserNotFoundException::new);
        playlistRepository.save(playlistRegisterRequestDto.toEntity(user));
    }

    public void createFirstPlaylist(User user) {
        playlistRepository.save(Playlist.builder()
                .coverIdx(CoverType.green)
                .decoIdx(DecoType.ribbon)
                .colorIdx(ColorType.coral)
                .letter("안녕하세요! 플리 보따리에 오신 여러분을 환영합니다.🎅\n" +
                        "\n" +
                        "플리 보따리는 연말을 맞아 친구나 지인에게 캐롤과 함께 비밀편지를 전달해주는 서비스입니다.\n" +
                        "\n" +
                        "이번 크리스마스에는 친구나 지인에게 노래와 함께 말하지 못했던 소중한 마음을 플리 보따리에 담아 전달해보세요.\n" +
                        "\n" +
                        "올 한해 마무리를 🎁플리 보따리🎁와 함께해보세요!")
                .friendname("플리보따리🎁")
                .spotifyId("3jImLeLoNu74fDyftw2Wuc")
                .user(user)
                .build()
        );
    }

    public List<Playlist> searchPlayListPackByNickname(String nickname) {
        User user = userRepository.findByNickname(nickname)
                        .orElseThrow(UserNotFoundException::new);
        return playlistRepository.findAllByUser(user);
    }

    public List<Playlist> searchPlayListPackByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        return playlistRepository.findAllByUser(user);
    }

    public Optional<Playlist> searchPlayListOne(Long nickname, Long playlistId) {
        return playlistRepository.findByUserNicknameAndPlaylistId(nickname, playlistId);
    }
}
