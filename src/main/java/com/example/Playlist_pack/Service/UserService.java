package com.example.Playlist_pack.Service;

import com.example.Playlist_pack.Domain.User;
import com.example.Playlist_pack.Dto.LoginDto;
import com.example.Playlist_pack.Dto.UserDto;
import com.example.Playlist_pack.Repository.UserRepository;
import java.util.Optional;
import lombok.Getter;
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

    public Response loginUser(LoginDto loginDto) {
        Optional<User> userOptional = userRepository.findByNickname(loginDto.getNickname());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (loginDto.getPassword().equals(user.getPassword())) {
                return new Response(user.getUserId(), "OK");
            } else {
                return new Response(-1L, "UNAUTHORIZED", "비밀번호가 일치하지 않습니다.");
            }
        } else {
            return new Response(-1L, "UNAUTHORIZED", "해당 닉네임을 가진 User가 존재하지 않습니다. ");
        }
    }
    public String getPasswordByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        return userOptional.map(User::getPassword).orElse(null);
    }

    @Getter
    public static class Response {
        private Long userId;
        private String status;
        private String error;

        public Response(Long userId, String status) {
            this.userId = userId;
            this.status = status;
        }

        public Response(Long userId, String status, String error) {
            this.userId = userId;
            this.status = status;
            this.error = error;
        }
    }
}
