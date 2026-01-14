package com.sagaman.user_stories.story.dto.response;

import java.util.UUID;

public class CommentResponse {

    public CommentResponse(UUID id, UUID storyId, Long authorId, String message) {
        this.id = id;
        this.storyId = storyId;
        this.authorId = authorId;
        this.message = message;
    }

    private final UUID id;
    private final UUID storyId;
    private final Long authorId;
    private final String message;


    public UUID getId() {
        return id;
    }

    public UUID getStoryId() {
        return storyId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getMessage() {
        return message;
    }
}
