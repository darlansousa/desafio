package com.picpaysimplifiedproject.picpaysimplifiedproject.controller;


import com.picpaysimplifiedproject.picpaysimplifiedproject.dtos.input.UserInputDTO;
import com.picpaysimplifiedproject.picpaysimplifiedproject.dtos.output.UserOutputDTO;
import com.picpaysimplifiedproject.picpaysimplifiedproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserOutputDTO> createUser(@RequestBody UserInputDTO user) {
        return ResponseEntity.ok(userService.createNewUser(user));
    }

    @GetMapping
    public ResponseEntity<List<UserOutputDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }
}
