package com.example.Playlist_pack.Service;

import com.example.Playlist_pack.Domain.Playlist;
import com.example.Playlist_pack.Dto.LetterDto;
import com.example.Playlist_pack.Repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LetterServiceImpl implements LetterService{

    private final PlaylistRepository playlistRepository;

    @Autowired
    public LetterServiceImpl(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public Playlist addLetter(Long userId, LetterDto letterDTO) {
        System.out.println("Received userId: " + userId);
        Playlist playlist = playlistRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        playlist.setLetter(letterDTO.getLetter());
        return playlistRepository.save(playlist);
    }

}
