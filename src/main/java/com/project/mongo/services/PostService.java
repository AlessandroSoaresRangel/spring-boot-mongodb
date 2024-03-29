package com.project.mongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mongo.domain.Post;
import com.project.mongo.repositories.PostRepository;
import com.project.mongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        Optional<Post> post = repository.findById(id);
        if (post.isEmpty()) {
            throw new ObjectNotFoundException("Post not found");
        }
        return post.get();
    }

    public List<Post> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public List<Post> fullSearch(String title, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return repository.fullSearch(title, minDate, maxDate);
    }
}
