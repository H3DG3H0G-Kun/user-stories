package com.sagaman.user_stories.story.exception;

import java.util.UUID;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(UUID id) {
        super("Comment not found by id " + id);
    }
}
