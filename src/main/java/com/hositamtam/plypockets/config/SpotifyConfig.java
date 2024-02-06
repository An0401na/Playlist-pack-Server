package com.hositamtam.plypockets.config;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

@Component
@Slf4j
public class SpotifyConfig {

    @Value("${spotify.registration.client-id}")
    private String CLIENT_ID;

    @Value("${spotify.registration.client-secret}")
    private String CLIENT_SECRET;

    private static SpotifyApi spotifyApi;

    private static String accessToken;
    private static Instant accessTokenExpiration;

    public static Instant getAccessTokenExpiration() {
        return accessTokenExpiration;
    }

    @PostConstruct
    public void initialize() {

        this.spotifyApi = new SpotifyApi.Builder().setClientId(CLIENT_ID).setClientSecret(CLIENT_SECRET).build();
        refreshAccessToken();
    }


    public static SpotifyApi getSpotifyApi() {
        if (spotifyApi == null || Instant.now().isAfter(accessTokenExpiration)) {
            refreshAccessToken();
        }
        return spotifyApi;
    }

    public static void refreshAccessToken() {
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
            accessToken = clientCredentials.getAccessToken();
            accessTokenExpiration = Instant.now().plusSeconds(clientCredentials.getExpiresIn());
            spotifyApi.setAccessToken(accessToken);
        } catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
            System.out.println("Error: " + e.getMessage());
            accessToken = "error";
        }
    }
}
