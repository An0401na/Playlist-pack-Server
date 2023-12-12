package com.example.Playlist_pack.Controller;

import com.example.Playlist_pack.Domain.Playlist;
import com.example.Playlist_pack.Global.dto.response.HttpResponse;
import com.example.Playlist_pack.Service.PlaylistPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistPackController {

    private final PlaylistPackService playlistPackService;

    @Autowired
    public PlaylistPackController(PlaylistPackService playlistPackService) {
        this.playlistPackService = playlistPackService;
    }

    @GetMapping("/{userId}")
    public HttpResponse<List<Playlist>> getPlaylistsByUserId(@PathVariable Long userId) {
        List<Playlist> playlists = playlistPackService.getPlaylistsByUserId(userId);
        return HttpResponse.okBuild(playlists);
    }
}
