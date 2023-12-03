package com.example.Playlist_pack.Controller;

import com.example.Playlist_pack.Domain.Playlist;
import com.example.Playlist_pack.Dto.LetterDto;
import com.example.Playlist_pack.Service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlists")
public class LetterController {

    private final LetterService letterService;

    @Autowired
    public LetterController(LetterService playlistService) {
        this.letterService = playlistService;
    }

    @PostMapping("/{userId}/letter")
    public ResponseEntity<Playlist> addLetter(
            @PathVariable Long userId,
            @RequestBody LetterDto letterDto
    ) {
        Playlist playlist = letterService.addLetter(userId, letterDto);
        return ResponseEntity.ok(playlist);
    }
}