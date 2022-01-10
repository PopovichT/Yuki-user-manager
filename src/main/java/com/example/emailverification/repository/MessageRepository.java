package com.example.emailverification.repository;

import com.example.emailverification.entity.MessagePost;
import com.example.emailverification.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<MessagePost, Long> {
    @Query(value="Select * FROM MESSAGE_POST WHERE LENGTH(MESSAGE )   = (SELECT MAX(length(MESSAGE))FROM MESSAGE_POST where USER_ID =id)", nativeQuery=true)
   List<MessagePost> findLongestMessage(Long id);
}
