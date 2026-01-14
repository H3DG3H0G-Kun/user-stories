package com.sagaman.user_stories.story.exception;

import com.sagaman.user_stories.story.enums.StoryStatus;

public class CanNotBeCommentedException extends RuntimeException {
    public CanNotBeCommentedException(StoryStatus status) {
        super("Story can't be commented when in " + status + " status");
    }
}
