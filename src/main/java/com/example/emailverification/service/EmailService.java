package com.example.emailverification.service;

import com.example.emailverification.entity.MessagePost;
import com.example.emailverification.entity.User;
import com.example.emailverification.repository.MessageRepository;
import com.example.emailverification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class EmailService {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final Pattern p = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");

    @Autowired
    public EmailService(UserRepository userRepository, MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }


    public Long addUserToDatabase(User user) {
        var bool = verifyEmail(user.getEmail());
        if (!bool) {
            throw new IllegalArgumentException("Incorrect email");
        }
        var result = userRepository.save(user);
        return result.getId();
    }

    public User getById(Long id) {
        var item = userRepository.findById(id);
        if (item.isPresent()) {
            return item.get();
        }
        throw new IllegalArgumentException("User id mismatch");
    }

    public User getByEmail(String email) {
        var itemOptional = userRepository.findByEmail(email);
        if (itemOptional.isPresent()) {
            return itemOptional.get();
        }
        throw new IllegalArgumentException("User email mismatch");
    }

    public List<User> findUserWithLongestEmail() {
        var itemOptional = userRepository.findUserWithLongestEmail();
        if (itemOptional.isEmpty()) {
            throw new IllegalArgumentException("No data");
        }
        return itemOptional;
    }

    public Boolean verifyEmail(String email) {
        var m = p.matcher(email);
        return m.matches();
    }

    public void userPostMessage(Long userId, String message) {
        var userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("No data");
        }
        var user = userOpt.get();

        var messagePost = MessagePost.builder()
                .message(message)
                .user(user)
                .build();

        messageRepository.save(messagePost);
    }
}
