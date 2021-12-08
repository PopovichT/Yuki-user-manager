package com.example.emailverification.service;

import com.example.emailverification.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
//TODO: dive into mockito
public class EmailServiceTest {

    @Autowired
    private EmailService service;

    @Test
    void canServiceVerifyEmailCorrectly() {
        assertFalse(service.verifyEmail("123"));
        assertTrue(service.verifyEmail("yuki@gmail.com"));
    }

    @Test
    void canServiceSaveUserAndFindByIdCorrectly() {
        service.addUserToDatabase(new User());
    }
}
