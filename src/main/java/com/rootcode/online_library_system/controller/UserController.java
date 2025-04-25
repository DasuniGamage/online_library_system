package com.rootcode.online_library_system.controller;

import com.rootcode.online_library_system.dto.UserRegistrationDto;
import com.rootcode.online_library_system.model.User;
import com.rootcode.online_library_system.service.UserService;
import com.rootcode.online_library_system.util.StandardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/v1/user")
@Tag(name = "User Controller", description = "User management APIs")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<StandardResponse> registerUser(@Valid @RequestBody UserRegistrationDto registrationDto) {
        User user = userService.registerUser(registrationDto);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success","User Registered"),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{email}")
    public ResponseEntity<StandardResponse> getUser(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        return new ResponseEntity<>(new StandardResponse(200, "Successfull",user),
                HttpStatus.FOUND);

    }


}
