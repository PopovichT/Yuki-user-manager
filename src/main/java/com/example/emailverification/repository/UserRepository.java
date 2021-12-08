package com.example.emailverification.repository;

import com.example.emailverification.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmailAddress(String emailAddress);


}
