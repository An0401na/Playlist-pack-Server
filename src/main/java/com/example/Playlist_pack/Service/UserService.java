package com.example.Playlist_pack.Service;

import com.example.Playlist_pack.Domain.User;
import com.example.Playlist_pack.Dto.UserDto;
import com.example.Playlist_pack.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(UserDto userDto) {
        User newUser = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .nickname(userDto.getNickname())
                .build();

        return userRepository.save(newUser);
    }
}
