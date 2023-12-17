
package com.example.Playlist_pack.Service;

import com.example.Playlist_pack.Domain.Playlist;
import com.example.Playlist_pack.Domain.User;
import com.example.Playlist_pack.Dto.request.PlaylistRegisterRequestDto;
import com.example.Playlist_pack.Global.exception.custom.user.UserNotFoundException;
import com.example.Playlist_pack.Repository.PlaylistRepository;
import com.example.Playlist_pack.Repository.UserRepository;
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

    public List<Playlist> searchPlayListPack(String nickname) {
        return playlistRepository.findAllByUser_Nickname(nickname);
    }

    public List<Playlist> searchPlayListPack(Long userId) {
        return playlistRepository.findAllByUser_UserId(userId);
    }

    public Optional<Playlist> searchPlayListOne(Long userId, Long playlistId) {
        return playlistRepository.findByUserIdAndPlaylistId(userId, playlistId);
    }
}
