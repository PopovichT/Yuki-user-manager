package com.example.emailverification.repository;

import com.example.emailverification.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
//TODO: add some tests to check searching by email
//TODO: dive into asserts
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    void testCanSaveAndFindUserById() {
        var user = new User();
        user.setEmail("klezovich@phystech.edu");
        user.setName("Arthur");
        var savedUser = repository.save(user);

        var foundedUserOptional = repository.findById(savedUser.getId());
        assertTrue(foundedUserOptional.isPresent());
        assertEquals("Arthur", foundedUserOptional.get().getName());
        assertEquals("klezovich@phystech.edu", foundedUserOptional.get().getEmail());
    }
}
