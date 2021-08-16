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

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


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
    public String getUserProfile(@PathVariable Long id,
                                 Principal accountDetail,
                                 Model model) {
        ApplicationUser userDetails = applicationUserService.getUserById(id); // visited user
        model.addAttribute("userDetails", userDetails);

        model.addAttribute("current", applicationUserService.findUserByName(accountDetail.getName()));

        return ("profile");
    }

    @GetMapping("/user/{currentId}/follow/{followId}")
    public RedirectView follow( @PathVariable long currentId,
                                @PathVariable long followId) {

        if (currentId != followId){
            ApplicationUser current = applicationUserService.getUserById(currentId);
            ApplicationUser follow = applicationUserService.getUserById(followId);
            current.getAccountsIFollow().add(follow);
            applicationUserService.createApplicationUser(current);
        }
        return new RedirectView("/users/{followId}");
    }

    @GetMapping("/feeds")
    public String getFeed(Principal accountDetail, Model model) {
        model.addAttribute("usersIFollow", applicationUserService.findUserByName(accountDetail.getName()).getAccountsIFollow());
        return "feeds";
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
        return ("myProfile");

    }


}
