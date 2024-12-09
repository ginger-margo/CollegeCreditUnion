package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.dto.UserDTO;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // entrance point from user journey
@RequiredArgsConstructor // constructor to initialize emissionService and others
@RequestMapping("/auth")
public class RegisterController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "register user")
    public ResponseEntity<Void> registerUser(@RequestBody UserDTO userDTO) {
        userService.registerUser(userDTO);
        return ResponseEntity.ok().build(); //will return some metadata

    }
}
