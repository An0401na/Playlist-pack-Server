package com.example.Playlist_pack.Dto.response;

import com.example.Playlist_pack.Domain.Playlist;
import com.example.Playlist_pack.Dto.SpotifySearchResponseDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PlaylistResponseDto(Long playlistId,
                                        String boxImageUrl,
                                        String title,
                                        String artistName,
                                        String imageUrl,
                                        String previewUrl,
                                        String friendname,
                                        String letter,
                                        LocalDateTime createdDate ) {
    public static PlaylistResponseDto of(Playlist playlist, SpotifySearchResponseDto spotifySearchResponseDto, String  s3url){
        return PlaylistResponseDto.builder()
                .playlistId(playlist.getPlaylistId())
                .boxImageUrl(s3url+"gift-box/"+playlist.getCoverIdx()+"_"+playlist.getDecoIdx()+"_"+playlist.getColorIdx()+".jpg")
                .title( spotifySearchResponseDto==null ? null : spotifySearchResponseDto.getTitle())
                .artistName(spotifySearchResponseDto==null ? null : spotifySearchResponseDto.getArtistName())
                .imageUrl(spotifySearchResponseDto==null ? null : spotifySearchResponseDto.getImageUrl())
                .previewUrl(spotifySearchResponseDto==null ? null : spotifySearchResponseDto.getPreviewUrl())
                .friendname(playlist.getFriendname())
                .letter(playlist.getLetter())
                .createdDate(playlist.getCreatedDate())
                .build();

    }
}
