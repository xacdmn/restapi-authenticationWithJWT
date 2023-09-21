package com.example.recuitment.controller;

import com.example.recuitment.model.request.user.RegistrationRequest;
import com.example.recuitment.model.request.user.SigninRequest;
import com.example.recuitment.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@Tag(name = "Authentication", description = "API for Login and Register")
//@SecurityRequirement(name = "Authorization")
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "Login user with username and password")
    @PostMapping("/login")
    public ResponseEntity<?> signIn(
            @Schema(example = "{" +
                    "\"username\":\"username1\"," +
                    "\"password\":\"password1\"" +
                    "}")
            @RequestBody SigninRequest signinRequest) {
        LOGGER.info("logging in");
        HashMap<String, String> response = new HashMap();
        if (userService.existsUsername(signinRequest.getUsername())) {
            if (!passwordEncoder.matches(signinRequest.getPassword(), userService.findByUsername(signinRequest.getUsername()).getPassword())) {
                response.put("error", "Password incorrect");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(userService.signin(signinRequest), HttpStatus.OK);
        } else {
            response.put("error", "user not found");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Registers a new user")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @Valid
            @Schema(example = "{" +
                    "\"username\":\"username1\"," +
                    "\"password\":\"password1\"" +
                    "}")
            @RequestBody RegistrationRequest registrationRequest) {
        HashMap<String, String> response = new HashMap();
        if (userService.existsUsername(registrationRequest.getUsername())) {
            response.put("error", "Username already used");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        LOGGER.info(registrationRequest.toString());
        registrationRequest.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        userService.save(registrationRequest);
        return new ResponseEntity<>("User Registered", HttpStatus.CREATED);
    }

}

