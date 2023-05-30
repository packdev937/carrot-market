package com.market.carrot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long id;

    String email;
    String password;
    String name;
    String phone;
    String nickname;
    Double temperature;

    @OneToMany(mappedBy = "user")
    private List<Item> items;
}
