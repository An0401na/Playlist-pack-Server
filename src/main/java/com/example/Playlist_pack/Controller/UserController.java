package com.example.Playlist_pack.Controller;

import com.example.Playlist_pack.Dto.LoginDto;
import com.example.Playlist_pack.Dto.UserDto;
import com.example.Playlist_pack.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
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
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        ResponseEntity<?> responseEntity = userService.loginUser(loginDto);

        return responseEntity;
    }
    @GetMapping("/get-password")
    public ResponseEntity<String> getPasswordByEmail(@RequestParam String email) {
        String password = userService.getPasswordByEmail(email);

        if (password != null) {
            return new ResponseEntity<>("사용자 비밀번호: " + password, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("해당 이메일로 등록된 회원이 없습니다.", HttpStatus.NOT_FOUND);
        }
    }
}
