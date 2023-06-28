package com.market.carrot.controller;

import com.market.carrot.dto.SignupRequestDto;
import com.market.carrot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
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

        return "redirect:/home";
    }
}
