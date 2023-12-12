package com.example.Playlist_pack.Service;

import com.example.Playlist_pack.Domain.Playlist;
import com.example.Playlist_pack.Domain.PlaylistPack;
import com.example.Playlist_pack.Global.exception.HttpExceptionCode;
import com.example.Playlist_pack.Global.exception.custom.BusinessException;
import com.example.Playlist_pack.Repository.PlaylistPackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistPackService {

    private final PlaylistPackRepository playlistPackRepository;

    @Autowired
    public PlaylistPackService(PlaylistPackRepository playlistPackRepository) {
        this.playlistPackRepository = playlistPackRepository;
    }

    public List<Playlist> getPlaylistsByUserId(Long userId) {
        PlaylistPack playlistPack = playlistPackRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new BusinessException(HttpExceptionCode.PLAYLISTPACK_NOT_FOUND));
        // 만약 유저에 대한 PlaylistPack이 없다면 예외 처리를 수행

        return playlistPack.getPlaylists();
    }
}
