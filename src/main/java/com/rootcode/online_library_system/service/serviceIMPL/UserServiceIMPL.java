package com.rootcode.online_library_system.service.serviceIMPL;

import com.rootcode.online_library_system.dto.UserLoginDto;
import com.rootcode.online_library_system.dto.UserRegistrationDto;
import com.rootcode.online_library_system.exception.NotFoundException;
import com.rootcode.online_library_system.model.User;
import com.rootcode.online_library_system.repository.UserRepository;
import com.rootcode.online_library_system.service.JWTService;
import com.rootcode.online_library_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder =new BCryptPasswordEncoder(12);

    @Override
    public User registerUser(UserRegistrationDto registrationDto) {
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setName(registrationDto.getName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(encoder.encode(registrationDto.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new NotFoundException("User with email " + email + " not found.");
        }
        return user;
    }

    @Override
    public String verify(UserLoginDto userLoginDto) {
        Authentication authentication=
                authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(
                        userLoginDto.getEmail(),userLoginDto.getPassword())));

        if(authentication.isAuthenticated()){
            return  jwtService.generateToken(userLoginDto.getEmail());
        }
        return "Failure";
    }


}
