package com.example.Playlist_pack.Controller;

import com.example.Playlist_pack.Dto.PlaylistDto;
import com.example.Playlist_pack.Service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/{userid}")
    public ResponseEntity<String> createPlaylist(@PathVariable("userid") String userId, @RequestBody PlaylistDto playlistDto) {
        Long playlistId = playlistService.createPlaylist(playlistDto);
        return new ResponseEntity<>("선물이 등록되었습니다.", HttpStatus.CREATED);
    }
}