package com.cimba.lazurite.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService service;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid RegistrationRequest request
            ) throws MessagingException {
        service.register(request);
        return ResponseEntity.accepted().build();
    }


}



//    @GetMapping("/login")
//    public String login(){
//        return "<h1>this is auth controller</h1>";
//    }
//
//    private AuthenticationManager authenticationManager;
//
//    @PostMapping("/login")
//    public String login(@RequestBody LoginRequestDto loginRequest) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPasswordHash())
//            );
//            return "Login successful";
//        } catch (AuthenticationException e) {
//            return "Invalid email or password";
//        }
//    }
