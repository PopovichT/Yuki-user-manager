package com.example.emailverification.repository;

import com.example.emailverification.entity.MessagePost;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Slf4j
public class MessageRepositoryTest {
@Autowired
    private MessageRepository repository;
 /*
@Test
    void postMessage (){
    MessagePost post = new MessagePost();
    post.setMessage("Posting message test");
    var savedpost=repository.save(post);
    var foundedPostOptional = repository.findById(savedpost.getId());
    assertTrue(foundedPostOptional.isPresent());
    assertEquals("Posting message test", foundedPostOptional.get().getMessage());
}

 */
    @Test
    void findLongestMessageOfUser(){
        var foundedMessage = repository.findLongestMessage(1L);
       assertTrue(foundedMessage.contains("\"Любовь — это пуля со смещенным центром, которая бьёт в сердце, выворачивает карманы и выходит боком.\""));
    }
    @Test
    void findAllMessagesOfUserTest (){
        var foundedList = repository.findAllMessages(1L);
        assertTrue(foundedList.contains(""));
    }
}
