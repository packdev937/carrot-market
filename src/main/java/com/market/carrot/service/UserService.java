package com.market.carrot.service;

import com.market.carrot.jwt.JwtUtil;
import com.market.carrot.dto.LoginRequestDto;
import com.market.carrot.dto.SignupRequestDto;
import com.market.carrot.entity.User;
import com.market.carrot.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    public void save(User user) {
        userRepository.save(user);
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public String signup(@Valid SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String nickname = signupRequestDto.getNickname();

        Optional<User> foundUsername = userRepository.findByUsername(username);
        if (foundUsername.isPresent()) {
            throw new IllegalArgumentException("이미 가입된 사용자입니다.");
        }
        Optional<User> foundNickname = userRepository.findByNickname(nickname);
        if (foundNickname.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }

        User newUser = new User(username, password, nickname);
        userRepository.save(newUser);
        return "회원가입완료";
    }

    public String login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다")
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀 번호가 옳지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getNickname()));
        return "로그인 성공";
    }
}
