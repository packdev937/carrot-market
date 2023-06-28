package com.market.carrot.controller;

import com.market.carrot.dto.LoginRequestDto;
import com.market.carrot.dto.SignupRequestDto;
import com.market.carrot.entity.User;
import com.market.carrot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    // Get, Post를 사용하여 회원가입 구현
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signupRequestDto", new SignupRequestDto());
        return "/users/signupForm";
    }

    @PostMapping("/signup")
    public String signup(@Valid SignupRequestDto signupRequestDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/users/signupForm";
        }

        try {
            userService.signup(signupRequestDto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/users/signupForm";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginRequestDto", new LoginRequestDto());
        return "/users/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid LoginRequestDto loginRequestDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/users/loginForm";
        }

        try {
            User loginUser = userService.login(loginRequestDto);
            log.info("login? {}", loginUser);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/users/loginForm";
        }

        // 로그인 성공 처리
        return "redirect:/main";
    }
}
