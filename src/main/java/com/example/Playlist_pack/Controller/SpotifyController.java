package com.example.Playlist_pack.Controller;

import com.example.Playlist_pack.Dto.SpotifySearchResponseDto;
import com.example.Playlist_pack.Global.dto.response.HttpResponse;
import com.example.Playlist_pack.Service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class SpotifyController {
    @Autowired
    SpotifyService spotifyService =new SpotifyService();

    @GetMapping("/search/{trackname}")
    public HttpResponse<List<SpotifySearchResponseDto>> searchTracksByTrackname(@PathVariable String trackname) throws IOException, ParseException, SpotifyWebApiException {
        return HttpResponse.okBuild(spotifyService.SearchByTrackname(trackname));
    }

    //기존 작성해둔 코드 입니다 ~ 나중에 지워주세요
//    @GetMapping("/search/{trackname}")
//    public List<SpotifySearchResponseDto>searchTracksByTrackname(@PathVariable String trackname) throws IOException, ParseException, SpotifyWebApiException {
//        return spotifyService.SearchByTrackname(trackname);
//    }

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

    @GetMapping("/KoreanHot100")
    public List<SpotifySearchResponseDto> getKoreanHot100chart()
            throws IOException, SpotifyWebApiException {
        return spotifyService.getKoreanHot100Chart();
    }


}
