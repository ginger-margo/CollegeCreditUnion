package org.example.config;

import org.example.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// loading user data from database for authentication
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username)
                .map(CustomUserDetails::new) // transforming User into CustomUserDetails
//                .map(user -> User.builder()
//                        .username(user.getName())
//                        .password(user.getPassword())
//                        .roles(user.getRole())
//                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
