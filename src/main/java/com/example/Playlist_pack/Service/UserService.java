package com.example.Playlist_pack.Service;

import com.example.Playlist_pack.Domain.User;
import com.example.Playlist_pack.Dto.LoginDto;
import com.example.Playlist_pack.Dto.UserDto;
import com.example.Playlist_pack.Repository.UserRepository;
import java.util.Optional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> loginUser(LoginDto loginRequest) {
        User user = userRepository.findByNickname(loginRequest.getNickname());

        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            return new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.FORBIDDEN);
        }

        if (user.getNickname().length() <= 2 || user.getNickname().length() >= 8) {
            return new ResponseEntity<>("닉네임 글자수는 2자 이상 8자 이하이어야 합니다.", HttpStatus.CONFLICT);
        }

        if (user.getPassword().length() < 8 || !containsDigitAndLetter(user.getPassword())) {
            return new ResponseEntity<>("비밀번호는 숫자와 영문자를 포함한 8자 이상이어야 합니다.", HttpStatus.CONFLICT);
        }

        // 해당 불가조건 모두 통과시 Statuscode 201 반환

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    private boolean containsDigitAndLetter(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$");
    }
}
