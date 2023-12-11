
package com.example.Playlist_pack.Service;

import com.example.Playlist_pack.Domain.Playlist;
import com.example.Playlist_pack.Domain.PlaylistPack;
import com.example.Playlist_pack.Domain.Song;
import com.example.Playlist_pack.Dto.PlaylistDto;
import com.example.Playlist_pack.Repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    public Long createPlaylist(PlaylistDto playlistDTO) {
        Playlist playlist = Playlist.builder()
                .coveridx(playlistDTO.getCoveridx())
                .decoIdx(playlistDTO.getDecoIdx())
                .colorIdx(playlistDTO.getColorIdx())
                .letter(playlistDTO.getLetter())
                .friendname(playlistDTO.getFriendname())
                .build();

        return playlistRepository.save(playlist).getPlaylistId();
    }


}
