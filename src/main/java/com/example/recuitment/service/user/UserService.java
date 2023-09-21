package com.example.recuitment.service.user;

import com.example.recuitment.model.response.token.JwtTokenDto;
import com.example.recuitment.model.user.AppUser;
import com.example.recuitment.model.request.user.RegistrationRequest;
import com.example.recuitment.model.request.user.SigninRequest;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    void save(RegistrationRequest registrationRequest);
    JwtTokenDto signin(SigninRequest signinRequest);
    boolean existsUsername(String username);
    AppUser findByUsername(String username);

}
