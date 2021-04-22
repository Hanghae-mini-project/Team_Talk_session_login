package com.team_talk.demo.controller;

import com.team_talk.demo.domain.User;
import com.team_talk.demo.dto.SignupRequestDto;
import com.team_talk.demo.repository.UserRepository;
import com.team_talk.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // 회원 조회
    @GetMapping("/api/users")
    public List<User> readUsers() {
        return userRepository.findAll();
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 요청 처리
    @PostMapping("/api/signup")
    public void registerUser(@RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
//
    }
    @GetMapping("/user/forbidden")
    public String forbidden() {
        return "forbidden";

    }
}