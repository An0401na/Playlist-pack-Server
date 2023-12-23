
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
                .coverIdx(CoverType.red)
                .decoIdx(DecoType.ribbon)
                .colorIdx(ColorType.white)
                .letter("만나서 반가워요! 플리보따리를 이용해 주셔서 감사합니다💝 따뜻한 연말 되세요🎁")
                .friendname("플리보따리🎁")
                .spotifyId("0bYg9bo50gSsH3LtXe2SQn")
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
