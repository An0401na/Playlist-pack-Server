package com.example.Playlist_pack.Service;

import com.example.Playlist_pack.Domain.Playlist;
import com.example.Playlist_pack.Dto.LetterDto;
import lombok.Setter;

public interface LetterService {
    Playlist addLetter(Long userId, LetterDto LetterDTO);

}

