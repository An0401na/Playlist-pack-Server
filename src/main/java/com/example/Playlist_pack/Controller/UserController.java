package com.example.Playlist_pack.Controller;

import com.example.Playlist_pack.Dto.LoginDto;
import com.example.Playlist_pack.Dto.UserDto;
import com.example.Playlist_pack.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
        return new ResponseEntity<>("회원가입이 완료됬습니다.", HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<UserService.Response> loginUser(@RequestBody LoginDto loginDto) {
        UserService.Response response = userService.loginUser(loginDto);

        if ("UNAUTHORIZED".equals(response.getStatus())) {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
