package com.example.emailverification.controller;

import com.example.emailverification.entity.User;
import com.example.emailverification.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
class EmailController {

    private final EmailService service;

    @Autowired
    public EmailController(EmailService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        return service.addUserToDatabase(user);
    }


    @GetMapping("{id}")
    public Optional<User> getUserById(@RequestParam Long userId){
        return service.getById(userId);
    }

    @GetMapping("{email}")
    public User getUserByEmail(@RequestParam String email){
        return service.getByEmail(email);
    }

}




