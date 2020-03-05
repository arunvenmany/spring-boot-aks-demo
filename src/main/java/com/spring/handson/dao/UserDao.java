package com.spring.handson.dao;

import com.spring.handson.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends MongoRepository<User, String> {

    User findByUsername(String username);
}
