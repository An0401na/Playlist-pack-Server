
package com.example.Playlist_pack.Service;

import com.example.Playlist_pack.Domain.User;
import com.example.Playlist_pack.Dto.request.PlaylistRegisterRequestDto;
import com.example.Playlist_pack.Repository.PlaylistRepository;
import com.example.Playlist_pack.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaylistService {


    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;

    public void createPlaylist(PlaylistRegisterRequestDto playlistRegisterRequestDto) {
        User user = userRepository.findById(playlistRegisterRequestDto.userId())
                .orElseThrow();

        playlistRepository.save(playlistRegisterRequestDto.toEntity(user));
    }
}
