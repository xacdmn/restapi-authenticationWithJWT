package com.example.recuitment.service.user;

import com.example.recuitment.config.security.JwtUtil;
import com.example.recuitment.model.response.token.JwtTokenDto;
import com.example.recuitment.model.user.AppUser;
import com.example.recuitment.model.request.user.RegistrationRequest;
import com.example.recuitment.model.request.user.SigninRequest;
import com.example.recuitment.model.user.UserDetailsImpl;
import com.example.recuitment.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void save(RegistrationRequest registrationRequest) {
        AppUser users = new AppUser(registrationRequest);
        users.setUsername(users.getUsername());
        users.setPassword(users.getPassword());
        userRepository.save(users);
    }

    @Override
    public JwtTokenDto signin(SigninRequest signinRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(),
                signinRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateAccessToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        LOGGER.info("User " + userDetails.getUsername() + " logged in.");
        LOGGER.info(token);
        return new JwtTokenDto(token,
                userDetails.getId(),
                userDetails.getUsername());
    }

    @Override
    public boolean existsUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public AppUser findByUsername(String username) {
        return userRepository.findUsersByUsername(username);
    }
}
