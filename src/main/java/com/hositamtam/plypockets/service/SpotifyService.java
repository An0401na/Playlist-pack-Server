package com.hositamtam.plypockets.service;

import com.hositamtam.plypockets.config.SpotifyConfig;
import com.hositamtam.plypockets.dto.SpotifyDtoMapper;
import com.hositamtam.plypockets.dto.SpotifySearchResponseDto;
import com.hositamtam.plypockets.global.exception.custom.spotify.SpotifyErrorException;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.*;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@Slf4j
public class SpotifyService {

    private final SpotifyConfig spotifyConfig;
    private  SpotifyApi spotifyApi;

    @Autowired
    public SpotifyService(SpotifyConfig spotifyConfig) {
        this.spotifyConfig = spotifyConfig;
        this.spotifyApi = spotifyConfig.getSpotifyApi();
    }
    private void checkAndRefreshToken() {
        if (spotifyApi.getAccessToken() == null || isTokenExpired()) {
            spotifyConfig.refreshAccessToken();
            this.spotifyApi=spotifyConfig.getSpotifyApi();
        }
    }

    private boolean isTokenExpired() {
        return Instant.now().isAfter(spotifyConfig.getAccessTokenExpiration());
    }



    public List<SpotifySearchResponseDto> SearchByTrackname(String trackname) {

        checkAndRefreshToken();

        List<SpotifySearchResponseDto> searchResponseDtoList = new ArrayList<>();

        try {
            SearchTracksRequest searchTrackRequest = spotifyApi.searchTracks(trackname)

                    .limit(50)
                    .build();

            Paging<Track> searchResult = searchTrackRequest.execute();
            Track[] tracks = searchResult.getItems();

            for (Track track : tracks) {
                String title = track.getName();
                String previewUrl=track.getPreviewUrl();
                String spotifyId=track.getId();


                AlbumSimplified album = track.getAlbum();
                ArtistSimplified[] artists = album.getArtists();
                String artistName = artists[0].getName();

                Image[] images = album.getImages();
                String imageUrl = (images.length > 0) ? images[0].getUrl() : "NO_IMAGE";

                String albumName = album.getName();


                searchResponseDtoList.add(SpotifyDtoMapper.toSearchDto(spotifyId,artistName, title, albumName, imageUrl,previewUrl));
            }

        } catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return searchResponseDtoList;
    }


    public List<SpotifySearchResponseDto> SearchByTracknameAndArtist(String trackname, String Artist) {
        checkAndRefreshToken();

        List<SpotifySearchResponseDto> searchResponseDtoList = new ArrayList<>();

        try {
            SearchTracksRequest searchTrackRequest = spotifyApi.searchTracks("track:" + trackname + " artist:" + Artist)
                    .limit(1)
                    .build();

            Paging<Track> searchResult = searchTrackRequest.execute();
            Track[] tracks = searchResult.getItems();

            for (Track track : tracks) {
                String title = track.getName();
                String previewUrl=track.getPreviewUrl();
                String spotifyId=track.getId();

                AlbumSimplified album = track.getAlbum();
                ArtistSimplified[] artists = album.getArtists();
                String artistName = artists[0].getName();

                Image[] images = album.getImages();
                String imageUrl = (images.length > 0) ? images[0].getUrl() : "NO_IMAGE";

                String albumName = album.getName();

                searchResponseDtoList.add(SpotifyDtoMapper.toSearchDto(spotifyId,artistName, title, albumName, imageUrl,previewUrl));
            }

        } catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return searchResponseDtoList;
    }

    public List<SpotifySearchResponseDto> searchByGenre(String genre) {
        checkAndRefreshToken();

        List<SpotifySearchResponseDto> searchResponseDtoList = new ArrayList<>();

        try {
            SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks("genre:" + genre)
                    .limit(50)
                    .build();

            Paging<Track> searchResult = searchTracksRequest.execute();
            Track[] tracks = searchResult.getItems();

            for (Track track : tracks) {
                String title = track.getName();
                String previewUrl = track.getPreviewUrl();
                String spotifyId=track.getId();

                AlbumSimplified album = track.getAlbum();
                ArtistSimplified[] artists = album.getArtists();
                String artistName = artists[0].getName();

                Image[] images = album.getImages();
                String imageUrl = (images.length > 0) ? images[0].getUrl() : "NO_IMAGE";

                String albumName = album.getName();

                searchResponseDtoList.add(SpotifyDtoMapper.toSearchDto(spotifyId,artistName, title, albumName, imageUrl, previewUrl));
            }

        } catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return searchResponseDtoList;
    }

    @Cacheable(cacheNames = "hot100Cache", key = "'hot100'")
    public List<SpotifySearchResponseDto> getHot100Chart() {
        checkAndRefreshToken();

        List<SpotifySearchResponseDto> searchResponseDtoList = new ArrayList<>();

        try {
            GetPlaylistRequest getHot100PlaylistRequest = spotifyApi.getPlaylist("37i9dQZEVXbMDoHDwVN2tF") // HOT 100 playlist ID
                    .build();

            Playlist hot100Playlist = getHot100PlaylistRequest.execute();
            List<PlaylistTrack> tracks = Arrays.asList(hot100Playlist.getTracks().getItems());

            for (PlaylistTrack playlistTrack : tracks) {
                Track track = (Track) playlistTrack.getTrack();
                String title = track.getName();
                String previewUrl = track.getPreviewUrl();
                String spotifyId=track.getId();

                AlbumSimplified album = track.getAlbum();
                ArtistSimplified[] artists = album.getArtists();
                String artistName = artists[0].getName();

                Image[] images = album.getImages();
                String imageUrl = (images.length > 0) ? images[0].getUrl() : "NO_IMAGE";

                String albumName = album.getName();

                searchResponseDtoList.add(SpotifyDtoMapper.toSearchDto(spotifyId,artistName, title, albumName, imageUrl, previewUrl));
            }

        } catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return searchResponseDtoList;
    }

    @Cacheable(cacheNames = "koreaHot100Cache", key = "'koreaHot100'")
    public List<SpotifySearchResponseDto> getKoreanHot100Chart() {
        checkAndRefreshToken();


        List<SpotifySearchResponseDto> searchResponseDtoList = new ArrayList<>();

        try {
            GetPlaylistRequest getHot100PlaylistRequest = spotifyApi.getPlaylist("37i9dQZEVXbJZGli0rRP3r") // HOT 100 playlist ID
                    .build();

            Playlist hot100Playlist = getHot100PlaylistRequest.execute();
            List<PlaylistTrack> tracks = Arrays.asList(hot100Playlist.getTracks().getItems());

            for (PlaylistTrack playlistTrack : tracks) {
                Track track = (Track) playlistTrack.getTrack();
                String title = track.getName();
                String previewUrl = track.getPreviewUrl();
                String spotifyId= track.getId();

                AlbumSimplified album = track.getAlbum();
                ArtistSimplified[] artists = album.getArtists();
                String artistName = artists[0].getName();

                Image[] images = album.getImages();
                String imageUrl = (images.length > 0) ? images[0].getUrl() : "NO_IMAGE";

                String albumName = album.getName();

                searchResponseDtoList.add(SpotifyDtoMapper.toSearchDto(spotifyId,artistName, title, albumName, imageUrl, previewUrl));
            }

        } catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return searchResponseDtoList;
    }

    public SpotifySearchResponseDto getTrackBySpotifyId(String spotifyId)  {
        checkAndRefreshToken();

        try {
            GetTrackRequest getTrackRequest = spotifyApi.getTrack(spotifyId).build();


            Track track = getTrackRequest.execute();



            String title = track.getName();

            String previewUrl = track.getPreviewUrl();
            String spotifyIdFromApi = track.getId();

            AlbumSimplified album = track.getAlbum();
            ArtistSimplified[] artists = album.getArtists();
            String artistName = artists[0].getName();

            Image[] images = album.getImages();
            String imageUrl = (images.length > 0) ? images[0].getUrl() : "NO_IMAGE";

            String albumName = album.getName();

            return SpotifyDtoMapper.toSearchDto(spotifyIdFromApi, artistName, title, albumName, imageUrl, previewUrl);

        } catch (RuntimeException | IOException | SpotifyWebApiException | ParseException e) {
            throw new SpotifyErrorException();

        }
    }



}
