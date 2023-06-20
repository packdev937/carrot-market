package com.market.carrot;

import com.market.carrot.entity.User;
import com.market.carrot.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        userRepository.save(new User("tester", "test1234", "tester"));
    }
}
