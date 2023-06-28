package com.market.carrot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {

    @NotBlank
    String email;

    @NotBlank
    String password;
}
