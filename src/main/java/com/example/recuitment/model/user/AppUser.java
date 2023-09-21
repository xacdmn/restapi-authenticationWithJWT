package com.example.recuitment.model.user;

import com.example.recuitment.model.request.user.RegistrationRequest;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "users")
@Data
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;

    public AppUser(RegistrationRequest registrationRequest) {
        this.username = registrationRequest.getUsername();
        this.password = registrationRequest.getPassword();
    }

    public AppUser() {

    }
}
