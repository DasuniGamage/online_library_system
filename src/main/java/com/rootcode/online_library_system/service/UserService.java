package com.rootcode.online_library_system.service;

import com.rootcode.online_library_system.dto.UserLoginDto;
import com.rootcode.online_library_system.dto.UserRegistrationDto;
import com.rootcode.online_library_system.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User registerUser(UserRegistrationDto registrationDto);

    User getUserByEmail(String email);

    Object verify(UserLoginDto userLoginDto);
}
