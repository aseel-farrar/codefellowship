package com.example.codefellowship.domain;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(columnDefinition = "text")
    private String postBody;

    @Transient
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    ApplicationUser applicationUser;

    public Post(String postBody) {
        this.postBody = postBody;
        this.createdAt = LocalDate.now();
    }

    public Post() {

    }

    public Long getPostId() {
        return postId;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
}
