package com.example.Playlist_pack.Domain;

import com.example.Playlist_pack.Global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Builder
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private String password;

    @Column(unique = true)
    private String nickname;

}
