package com.hositamtam.plypockets.config;

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

@Slf4j
@Component
public class SpotifyConfig {

    private static String CLIENT_ID ="9aa067b28e444056ab123c76058ca7ab";

    private static String CLIENT_SECRET ="28da9a874ab248ecae461398a4a20d53";


//    @Value("${spotify.registration.client-id}")
//    public void setClientId(String clientId) {
//        CLIENT_ID=clientId;
//    }
//
//    @Value("${spotify.registration.client-secret}")
//    public void setClientSecret(String clientSecret) {
//        CLIENT_SECRET=clientSecret;
//    }
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(CLIENT_ID).setClientSecret(CLIENT_SECRET).build();

    private static String accessToken;
    private static Instant accessTokenExpiration;

    public static String getAccessToken(){
        if (accessToken == null || Instant.now().isAfter(accessTokenExpiration)) {
            refreshAccessToken();
        }
        return accessToken;
    }

    private static void refreshAccessToken() {
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
        log.info("새로운 리프레쉬 토큰 발급을 위한 과정 시작");
        log.info("현재 정의된 Client-id : "+CLIENT_ID);
        log.info("현재 정의된 Client-secret : "+CLIENT_SECRET);

        try {

            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            log.info("0");
            accessToken = clientCredentials.getAccessToken();
            log.info("현재 발급된 토큰 : "+accessToken);
            // 토큰의 유효 시간을 계산하여 저장합니다.
            accessTokenExpiration = Instant.now().plusSeconds(clientCredentials.getExpiresIn());
            log.info("2");

            spotifyApi.setAccessToken(accessToken);
            log.info("3");

        } catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e) {
            System.out.println("Error: " + e.getMessage());
            accessToken = "error";
        }
    }
}
