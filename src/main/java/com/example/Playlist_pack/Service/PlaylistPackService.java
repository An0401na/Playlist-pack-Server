package com.example.Playlist_pack.Service;

import com.example.Playlist_pack.Domain.Playlist;
import com.example.Playlist_pack.Domain.PlaylistPack;
import com.example.Playlist_pack.Repository.PlaylistPackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaylistPackService {

    private final PlaylistPackRepository playlistPackRepository;

    @Autowired
    public PlaylistPackService(PlaylistPackRepository playlistPackRepository) {
        this.playlistPackRepository = playlistPackRepository;
    }

    public List<Playlist> getPlaylistsByUserId(Long userId) {
        Optional<PlaylistPack> optionalPlaylistPack = playlistPackRepository.findByUserId(userId);

        if (optionalPlaylistPack.isPresent()) {
            PlaylistPack playlistPack = optionalPlaylistPack.get();
            return playlistPack.getPlaylists();
        } else {
            // 만약 유저에 대한 PlaylistPack이 없다면 빈 리스트를 반환하거나 예외 처리를 수행할 수 있습니다.
            return List.of();
        }
    }
}
