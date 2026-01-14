package com.sagaman.user_stories.story.entity;

import com.sagaman.user_stories.story.enums.StoryStatus;
import com.sagaman.user_stories.user.entity.User;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_stories")
public class Story {

    protected Story() {}

    public Story(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = StoryStatus.TODO;
    }

    public void assignTo(User assignee) {
        this.assignee = assignee;
    }

    public void changeStatusTo(StoryStatus status) {
        this.status = status;
    }


    @Id
    @UuidGenerator
    private UUID id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StoryStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;


    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "assignee_user_id")
    private User assignee;

    @Version
    private long version;

    public UUID getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public StoryStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public User getAssignee() {
        return assignee;
    }

    public long getVersion() {
        return version;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
