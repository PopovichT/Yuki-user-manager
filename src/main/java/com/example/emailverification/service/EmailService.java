package com.example.emailverification.service;

import com.example.emailverification.entity.User;
import com.example.emailverification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class EmailService {
    private final UserRepository repository;
    private final Pattern p = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");

    @Autowired
    public EmailService(UserRepository repository) {
        this.repository = repository;
    }


    public Long addUserToDatabase(User user) {
        var bool = verifyEmail(user.getEmail());
        if (!bool) {
            throw new IllegalArgumentException("Incorrect email");
        }
        var result = repository.save(user);
        return result.getId();
    }

    public User getById(Long id) {
        var item = repository.findById(id);
        if (item.isPresent()) {
            return item.get();
        }
        throw new IllegalArgumentException("User id mismatch");
    }

    public User getByEmail(String email) {
        var itemOptional = repository.findByEmail(email);
        if (itemOptional.isPresent()) {
            return itemOptional.get();
        }
        throw new IllegalArgumentException("User email mismatch");
         }

public List<User> findLongestUserEmail(Long id){
    var itemOptional = repository.findLongestUserEmail(id);
    if (itemOptional.isEmpty()) {
        throw new IllegalArgumentException("No data");
    }
    return itemOptional;
    }



    public Boolean verifyEmail(String email) {
        var m = p.matcher(email);
        return m.matches();
    }
}
