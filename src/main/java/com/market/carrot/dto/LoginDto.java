package com.market.carrot.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDto {
    @NotEmpty
    String email;

    @NotEmpty
    String password;

}
