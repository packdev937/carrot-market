package com.market.carrot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequestDto {

    @NotBlank(message = "이메일 입력은 필수입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    String email;

    @NotBlank(message = "비밀번호 입력을 필수입니다.")
    String password;

    @Size(min = 3, max = 10, message = "3자에서 10자 이내로 작성해주세요")
    String nickname;

    @NotBlank
    String phone;

    @NotBlank
    String name;
}
