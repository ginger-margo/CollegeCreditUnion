package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.config.CustomUserDetailsService;
import org.example.dto.UserDTO;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // entrance point from user journey
@RequiredArgsConstructor // constructor to initialize emissionService and others
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping("/all")
    public ResponseEntity<List<String>> showAllUsers (){
        List<String> users = userService.findAllNames();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/update-password")
    public ResponseEntity<Void> editPassword(@RequestBody UserDTO userDTO){
        // only an admin can update a user or the user themselves
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!currentUser.getName().equals(userDTO.name()) && !currentUser.getRole().equals("ADMIN")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        userService.editPassword(userDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestBody UserDTO userDTO){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!currentUser.getRole().equals("ADMIN")) {
            userService.deleteUser(userDTO);
        }
        return ResponseEntity.ok().build();
    }

}
