package com.example.emailverification.repository;

import com.example.emailverification.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(value="Select * FROM USER WHERE LENGTH(EMAIL) = (SELECT MAX(length(EMAIL)) FROM USER)", nativeQuery=true)
    List<User> findUserWithLongestEmail();

}

