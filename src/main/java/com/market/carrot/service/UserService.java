package com.market.carrot.service;

import com.market.carrot.dto.LoginRequestDto;
import com.market.carrot.dto.SignupRequestDto;
import com.market.carrot.entity.User;
import com.market.carrot.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User signup(@Valid SignupRequestDto signupRequestDto) {
        String name = signupRequestDto.getName();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        String email = signupRequestDto.getEmail();
        String nickname = signupRequestDto.getNickname();
        String phone = signupRequestDto.getPhone();

        // 중복된 이메일로 회원 가입 요청이 있을 때
        Optional<User> findEmail = userRepository.findByEmail(email);
        if (findEmail.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }

        // 중복된 닉네임으로 회원 가입 요청이 있을 때
        Optional<User> findNickname = userRepository.findByNickname(nickname);
        if (findNickname.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }

        // 중복된 폰번호로 회원 가입 요청이 있을 때
        Optional<User> findPhone = userRepository.findByPhone(phone);
        if (findPhone.isPresent()) {
            throw new IllegalArgumentException("이미 가입된 핸드폰 번호입니다.");
        }

        // Builder를 통한 새로운 User 객체 생성
        User newUser = User.builder()
                .name(name)
                .email(email)
                .password(password)
                .nickname(nickname)
                .phone(phone)
                .build();

        return userRepository.save(newUser);
    }

    public User login(@Valid LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

//        Optional<User> findUser = userRepository.findByEmail(email);
//        if (findUser.isEmpty()) {
//            throw new IllegalArgumentException("일치하는 회원 정보가 없습니다.");
//        }

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }
}

// Reference: https://pamyferret.tistory.com/67
