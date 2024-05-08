package com.workintech.s19d2.controlller;

import com.workintech.s19d2.dto.RegisterResponse;
import com.workintech.s19d2.dto.RegistrationMember;
import com.workintech.s19d2.entity.Member;
import com.workintech.s19d2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegistrationMember registrationMember){
        try {
            Member newMember = userService.registerNewMember(registrationMember);
            return ResponseEntity.ok(new RegisterResponse("Registration successful", newMember.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new RegisterResponse("Registration failed: " + e.getMessage(), null));
        }
    }
    }

