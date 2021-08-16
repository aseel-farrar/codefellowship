package com.example.codefellowship.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String bio;


    @OneToMany(mappedBy = "applicationUser")
    Set<Post> posts = new HashSet<>();

    @ManyToMany
    @JoinTable (
            name="followers",
            joinColumns = { @JoinColumn(name = "follower_id")},
            inverseJoinColumns = { @JoinColumn(name = "followee_id")}
    )
    List<ApplicationUser> accountsIFollow;

    @ManyToMany(mappedBy = "accountsIFollow")
    List<ApplicationUser> accountsFollowMe;

    public ApplicationUser(String username, String password, String firstName, String lastName, Date dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    public ApplicationUser() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

//    public void createPost(String post) {
//        posts.add(new Post(post));
//    }


    public List<ApplicationUser> getAccountsIFollow() {
        return accountsIFollow;
    }

    public void setAccountsIFollow(List<ApplicationUser> accountsIFollow) {
        this.accountsIFollow = accountsIFollow;
    }

    public List<ApplicationUser> getAccountsFollowMe() {
        return accountsFollowMe;
    }

    public void setAccountsFollowMe(List<ApplicationUser> accountsFollowMe) {
        this.accountsFollowMe = accountsFollowMe;
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", bio='" + bio + '\'' +
                '}';
    }
}
