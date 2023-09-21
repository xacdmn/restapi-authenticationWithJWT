package com.example.recuitment.model.response.token;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class JwtTokenDto {

    private String accessToken;
    private String type = "Bearer";
    private Long id;
    private String username;

    public JwtTokenDto(String accessToken, Long id, String username) {
        this.accessToken = accessToken;
        this.id = id;
        this.username = username;
    }

    public JwtTokenDto() {

    }

}
