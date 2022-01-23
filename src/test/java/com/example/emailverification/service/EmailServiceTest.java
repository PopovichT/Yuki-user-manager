package com.example.emailverification.service;

import com.example.emailverification.entity.User;
import com.example.emailverification.mother.UserObjectMother;
import com.example.emailverification.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
//TODO: dive into mockito
//TODO: use simple mocks in demo tests (baeldung) https://www.baeldung.com/mockito-series
public class EmailServiceTest {

    @Autowired
    private EmailService service;

    @MockBean
    private UserRepository repository;

    @SpyBean
    private EmailService spyService;

    @Test
    void canServiceVerifyEmailCorrectly() {
        assertFalse(service.verifyEmail("123"));
        assertTrue(service.verifyEmail("yuki@gmail.com"));
    }

    @Test
    void canServiceSaveUserAndFindByIdCorrectly() {
        var user = new User();
        user.setEmail("eugene@gmail.com");
        user.setName("Eugene");

        var userFromRepository = user;
        userFromRepository.setId(1L);

        when(repository.save(user)).thenReturn(userFromRepository);
        var result = service.addUserToDatabase(user);
        assertEquals(result, 1L);
    }


    @Test
    void couldServiceTrowExceptionWhenAddingUserWithInvalidEmail() {
        var user = new User();
        user.setEmail("eugene@@@@....");
        user.setName("Eugene");

        assertThatThrownBy(() -> service.addUserToDatabase(user)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void couldServiceVerifyEmailWhenCallingAdd() {
        var user = new User();
        user.setEmail("eugene@gmail.com");
        user.setName("Eugene");

        var userFromRepository = user;
        userFromRepository.setId(1L);

        when(repository.save(user)).thenReturn(userFromRepository);

        spyService.addUserToDatabase(user);

        verify(spyService).verifyEmail(any());
    }


}
