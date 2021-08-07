package com.example.codefellowship.web;

import com.example.codefellowship.Infrastructure.services.PostService;
import com.example.codefellowship.domain.ApplicationUser;
import com.example.codefellowship.Infrastructure.services.ApplicationUserService;
import com.example.codefellowship.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Date;
import java.util.ArrayList;


@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserService applicationUserService;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    PostService postService;


    @GetMapping("/signupPage")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView createApplicationUser(@RequestParam String username,
                                              @RequestParam String password,
                                              @RequestParam String firstname,
                                              @RequestParam String lastname,
                                              @RequestParam Date dateOfBirth,
                                              @RequestParam String bio) {

        ApplicationUser applicationUser = new ApplicationUser(username,
                encoder.encode(password),
                firstname,
                lastname,
                dateOfBirth,
                bio);
        applicationUserService.createApplicationUser(applicationUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(applicationUser, null, applicationUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/myProfile");
    }

    @PostMapping("/myProfile")
    public RedirectView createNewPost(@RequestParam String postBody, Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        ApplicationUser applicationUser = applicationUserService.findUserByName(userDetails.getUsername());
        Post newPost = new Post(postBody);
        newPost.setApplicationUser(applicationUser);
        postService.savePost(newPost);

        model.addAttribute("userDetails",applicationUser);
        model.addAttribute("posts",applicationUser.getPosts());
        return new RedirectView("/myProfile");
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/users/{id}")
    public String getUserProfile(@PathVariable Long id, Model model) {
        ApplicationUser userDetails = applicationUserService.getUserById(id);
        model.addAttribute("userDetails", userDetails);
        return ("profile");
    }


    @GetMapping("/myProfile")
    public String getProfile(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        ApplicationUser applicationUser = applicationUserService.findUserByName(userDetails.getUsername());
        model.addAttribute("userDetails",applicationUser);
        model.addAttribute("posts",applicationUser.getPosts());
        return ("profile");

    }


}
