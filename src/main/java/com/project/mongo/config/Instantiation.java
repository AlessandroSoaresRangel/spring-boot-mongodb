package com.project.mongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.project.mongo.domain.Post;
import com.project.mongo.domain.User;
import com.project.mongo.dto.AuthorDto;
import com.project.mongo.dto.CommentDto;
import com.project.mongo.repositories.PostRepository;
import com.project.mongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("21/03/2023"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!",
                new AuthorDto(maria));
        Post post2 = new Post(null, sdf.parse("23/03/2023"), "Bom dia", "Acordei feliz hoje!", new AuthorDto(maria));

        CommentDto comment1 = new CommentDto("Boa viagem, mano!", sdf.parse("21/03/2023"), new AuthorDto(alex));
        CommentDto comment2 = new CommentDto("Aproveite!", sdf.parse("22/03/2023"), new AuthorDto(bob));
        CommentDto comment3 = new CommentDto("Tenha um otimo dia!", sdf.parse("23/03/2023"), new AuthorDto(alex));

        post1.getComment().addAll(Arrays.asList(comment1, comment2));
        post2.getComment().add(comment3);

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }

}
