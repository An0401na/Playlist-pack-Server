package com.hositamtam.plypockets.playlist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hositamtam.plypockets.controller.PlaylistController;
import com.hositamtam.plypockets.domain.Playlist;
import com.hositamtam.plypockets.dto.request.PlaylistRegisterRequestDto;
import com.hositamtam.plypockets.dto.response.PlaylistBoxResponseDto;
import com.hositamtam.plypockets.dto.response.PlaylistOneResponseDto;
import com.hositamtam.plypockets.dto.response.PlaylistResponseDto;
import com.hositamtam.plypockets.global.dto.response.HttpResponse;
import com.hositamtam.plypockets.service.PlaylistService;
import com.hositamtam.plypockets.service.SpotifyService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/playlists")
class PlaylistControllerTest {

    @Mock
    private PlaylistService playlistService;

    @Mock
    private SpotifyService spotifyService;

    @Value("${custom.s3url}")
    private String s3url;

    @InjectMocks
    private PlaylistController playlistController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(playlistController).build();
    }

    @Test
    void testCreatePlaylist() throws Exception {
        PlaylistRegisterRequestDto requestDto = new PlaylistRegisterRequestDto(
                "1",
                "2",
                "3",
                "메리크리스마스",
                "벨라",
                "spotify:track:abc123");

        mockMvc.perform(post("/playlists/{nickname}", "exampleNickname")
                        .content(asJsonString(requestDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    // Helper method to convert objects to JSON
    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
