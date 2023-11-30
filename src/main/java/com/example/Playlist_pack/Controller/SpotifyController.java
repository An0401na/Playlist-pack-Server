package com.example.Playlist_pack.Controller;

import com.example.Playlist_pack.Dto.SpotifySearchResponseDto;
import com.example.Playlist_pack.Service.SpotifyService;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

@CrossOrigin(origins = "*")
@RestController
public class SpotifyController {
    @Autowired
    SpotifyService spotifyService =new SpotifyService();

    @GetMapping("/search/{trackname}")
    public List<SpotifySearchResponseDto>searchTracksByTrackname(@PathVariable String trackname) throws IOException, ParseException, SpotifyWebApiException {


        return spotifyService.SearchByTrackname(trackname);
    }

    @GetMapping("/search/{artist}/{trackname}")
    public List<SpotifySearchResponseDto> searchTracksByTracknameAndArtist(@PathVariable String trackname,@PathVariable String artist) throws IOException, ParseException, SpotifyWebApiException {


        return spotifyService.SearchByTracknameAndArtist(trackname,artist);
    }


    @GetMapping("/search/genre/{genre}")
    public List<SpotifySearchResponseDto> searchTracksByGenre(@PathVariable String genre)
            throws IOException, SpotifyWebApiException {
        return spotifyService.searchByGenre(genre);
    }
    @GetMapping("/TodayHot100")
    public List<SpotifySearchResponseDto> getHot100Chart()
            throws IOException, SpotifyWebApiException {
        return spotifyService.getHot100Chart();
    }


}
