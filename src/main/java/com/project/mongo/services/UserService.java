package com.project.mongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mongo.domain.User;
import com.project.mongo.dto.UserDto;
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

    public User insert(User user) {
        return repository.insert(user);
    }

    public User update(User userSource) {
        User userFromDb = findById(userSource.getId());
        updateData(userFromDb, userSource);
        return repository.save(userFromDb);
    }

    private void updateData(User userFromDb, User userSource) {
        userFromDb.setName(userSource.getName());
        userFromDb.setEmail(userSource.getEmail());
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public User fromDto(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
    }
}
