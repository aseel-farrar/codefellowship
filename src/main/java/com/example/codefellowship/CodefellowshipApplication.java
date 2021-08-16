package com.example.codefellowship;

import com.example.codefellowship.Infrastructure.ApplicationUserRepository;
import com.example.codefellowship.Infrastructure.services.PostService;
import com.example.codefellowship.domain.ApplicationUser;
import com.example.codefellowship.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;

@SpringBootApplication
public class CodefellowshipApplication implements CommandLineRunner {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    PostService postService;

    public static void main(String[] args) {
        SpringApplication.run(CodefellowshipApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ApplicationUser adminUser = new ApplicationUser("aseel",
                encoder.encode("0000"),
                "aseel", "farrar", Date.valueOf("1993-3-8"),
                "software developer");

        applicationUserRepository.save(adminUser);
        Post newPost = new Post("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        newPost.setApplicationUser(adminUser);
        postService.savePost(newPost);

        ApplicationUser applicationUser = new ApplicationUser("nawras",
                encoder.encode("0000"),
                "nawras", "farrar", Date.valueOf("2020-6-12"),
                "cutest kid ever");

        applicationUserRepository.save(applicationUser);
        Post newPost2 = new Post("Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");

        newPost2.setApplicationUser(applicationUser);
        postService.savePost(newPost2);
    }
}