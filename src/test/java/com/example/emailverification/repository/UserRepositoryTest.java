package com.example.emailverification.repository;

import com.example.emailverification.entity.User;
import com.example.emailverification.mother.UserObjectMother;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Slf4j
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

    @Test
    void foundUserEmailTest() {
        var user = new User();
        user.setEmail("klezovich@phystech.edu");
        user.setName("Arthur");
        var savedUser = repository.save(user);
        var foundedUsersEmail = repository.findByEmail(savedUser.getEmail());
        assertTrue(foundedUsersEmail.isPresent());
        assertEquals("klezovich@phystech.edu", foundedUsersEmail.get().getEmail());
    }

    @Test
    void foundLongestEmailTest() {
        var user = UserObjectMother.valid().name("Arthur").email("klezovich@phystech.edu").build();
        var user2 = UserObjectMother.valid().name("Taras").email("t@t.ru").build();
        repository.save(user);
        repository.save(user2);
        var foundedUsers = repository.findUserWithLongestEmail();

        assertEquals(foundedUsers.size(), 1);
        assertEquals(foundedUsers.get(0).getEmail(), user.getEmail());
    }

    @Test
    void foundLongEmail2Test() {
        var user = UserObjectMother.valid().name("Arthur").email("klezovich@phystech.edu").build();
        var user2 = UserObjectMother.valid().name("qweqwe").email("qwezovich@phystech.edu").build();
        var user3 = UserObjectMother.valid().name("Taras").email("t@t.ru").build();
        repository.save(user);
        repository.save(user2);
        repository.save(user3);
        var foundedUsers = repository.findUserWithLongestEmail();
        log.info(foundedUsers.toString());
        assertEquals(foundedUsers.size(), 2);
    }
}


