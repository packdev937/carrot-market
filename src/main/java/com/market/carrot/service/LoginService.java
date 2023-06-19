package com.market.carrot.service;

import com.market.carrot.entity.User;
import com.market.carrot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    // 로그인 이메일로 찾은 User Entity의 비밀번호가 로그인 비밀번호와 맞다면 리턴, 아니라면 Null을 리턴한다
    public User login(String loginEmail, String password) {
        return userRepository.findUserByEmail(loginEmail)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
    }
}
