package com.market.carrot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter //@Setter를 사용하지 않기
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 아무런 값도 갖지 않는 의미 없는 객체의 생성을 막음
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long id;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String password;

    @Column(nullable = false, unique = true)
    String nickname;

    @Column(nullable = false, unique = true)
    String phone;

    @Column(nullable = false)
    String name;

    @Builder // 생성자에 Builder를 붙임으로써 AllArgsConstructor와의 동시 사용을 막음
    public User(String email, String password, String nickname, String phone, String name) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.name = name;
    }
}

// Reference: https://velog.io/@mooh2jj/%EC%98%AC%EB%B0%94%EB%A5%B8-%EC%97%94%ED%8B%B0%ED%8B%B0-Builder-%EC%82%AC%EC%9A%A9%EB%B2%95
