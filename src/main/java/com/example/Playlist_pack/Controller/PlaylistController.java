package com.example.Playlist_pack.Controller;

import com.example.Playlist_pack.Domain.Playlist;
import com.example.Playlist_pack.Service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/{userId}/{playlistId}")
    public Playlist getPlaylistByUserIdAndPlaylistId(
            @PathVariable String userId,
            @PathVariable Long playlistId
    ) {
        return playlistService.getPlaylistByUserIdAndPlaylistId(userId, playlistId);
    }
}
