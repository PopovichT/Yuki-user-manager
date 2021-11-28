package com.example.emailverification.service;

import com.example.emailverification.entity.User;
import com.example.emailverification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class EmailService {
    private final UserRepository repository;
    private final Pattern p = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");

    @Autowired
    public EmailService(UserRepository repository) {
        this.repository = repository;
    }

    public String addUserToDatabase(User user) {
        var bool = verifyEmail(user.getEmail());
        if (!bool) {
            return "FAIL";
        }
        repository.save(user);
        return "SUCCESS";
    }

    public Boolean verifyEmail(String email) {
        var m = p.matcher(email);
        return m.matches();
    }
}
