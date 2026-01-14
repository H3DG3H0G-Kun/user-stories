package com.sagaman.user_stories.story.entity;

import com.sagaman.user_stories.user.entity.User;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_story_comments")
public class StoryComment {

    protected StoryComment() {
    }

    public StoryComment(Story story, User user, String message) {
        this.story = story;
        this.author = user;
        this.message = message;
    }

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "story_id", nullable = false)
    private Story story;

    @ManyToOne
    @JoinColumn(name = "author_user_id", nullable = false)
    private User author;

    private String message;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public Story getStory() {
        return story;
    }

    public User getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
