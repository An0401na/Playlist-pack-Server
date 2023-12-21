
package com.hositamtam.plypockets.service;

import com.hositamtam.plypockets.domain.Playlist;
import com.hositamtam.plypockets.domain.User;
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
