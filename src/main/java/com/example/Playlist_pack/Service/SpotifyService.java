package com.example.Playlist_pack.Service;

import com.example.Playlist_pack.Config.SpotifyConfig;
import com.example.Playlist_pack.Dto.SpotifyDtoMapper;
import com.example.Playlist_pack.Dto.SpotifySearchResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
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
@Slf4j
public class SpotifyService {
    public List<SpotifySearchResponseDto> SearchByTrackname(String trackname) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(SpotifyConfig.getAccessToken())
                .build();

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
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(SpotifyConfig.getAccessToken())
                .build();

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
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(SpotifyConfig.getAccessToken())
                .build();

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
        log.info("투데이 핫 100 호출");
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(SpotifyConfig.getAccessToken())
                .build();

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
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(SpotifyConfig.getAccessToken())
                .build();

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

    public SpotifySearchResponseDto getTrackBySpotifyId(String spotifyId) {
        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setAccessToken(SpotifyConfig.getAccessToken())
                .build();

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

        } catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
            System.out.println("Error: " + e.getMessage());
            // Handle the error or throw an exception as needed
            return null;
        }
    }



}
