package com.sagaman.user_stories.story.dto.response;

import com.sagaman.user_stories.story.enums.StoryStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class StoryResponse {

    public StoryResponse(UUID id, String title, String description, StoryStatus status, Long assigneeId, LocalDateTime createdAt, LocalDateTime updatedAt, long version) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.assigneeId = assigneeId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.version = version;
    }

    private final UUID id;
    private final String title;
    private final String description;
    private final StoryStatus status;
    private final Long assigneeId;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final long version;

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

    public Long getAssigneeId() {
        return assigneeId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public long getVersion() {
        return version;
    }
}
