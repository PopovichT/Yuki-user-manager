package com.example.emailverification.repository;

import com.example.emailverification.entity.MessagePost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<MessagePost, Long> {
}
