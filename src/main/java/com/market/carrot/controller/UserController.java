package com.market.carrot.controller;

import com.market.carrot.dto.LoginRequestDto;
import com.market.carrot.dto.ResponseDto;
import com.market.carrot.dto.SignupRequestDto;
import com.market.carrot.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/users/signin")
    public ResponseDto<String> signin(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return ResponseDto.success(userService.signup(signupRequestDto));
    }

    @PostMapping("/users/login")
    public ResponseDto<String> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return ResponseDto.success(userService.login(loginRequestDto, response));
    }
}
