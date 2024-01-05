package com.project.mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mongo.domain.User;
import com.project.mongo.repositories.UserRepository;
import com.project.mongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {

        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            throw new ObjectNotFoundException("Object not found");
        }

        return user.get();
    }
}
