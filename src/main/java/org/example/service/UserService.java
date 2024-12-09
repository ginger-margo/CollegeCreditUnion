package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDTO;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(UserDTO userDTO){
        userRepository.save(User.builder()
                        .name(userDTO.name())
                        .password(passwordEncoder.encode(userDTO.password()))
                        .role("USER")
                .build());
    }

    public List<String> findAllNames(){
        return userRepository.findAll()
                .stream()
                .map(User::getName)
                .toList(); // getting name from each user
    }

    public void editPassword(UserDTO userDTO) {
        Optional<User> userOpt = userRepository.findByName(userDTO.name());
        userOpt.ifPresent(user -> user.setPassword(passwordEncoder.encode(userDTO.password())));
    }

    public void deleteUser(UserDTO userDTO) {
        userRepository.deleteByName(userDTO.name());
    }
}
