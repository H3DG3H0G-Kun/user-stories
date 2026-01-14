package com.sagaman.user_stories.story.exception;

import com.sagaman.user_stories.story.enums.StoryStatus;

public class StoryAlreadyFinishedException extends RuntimeException{
    public StoryAlreadyFinishedException(StoryStatus status) {
        super("Story is in terminal state: " + status);
    }
}
