package com.sagaman.user_stories.story.exception;

import com.sagaman.user_stories.story.enums.StoryStatus;

public class InvalidStoryTransitionException extends RuntimeException {
    public InvalidStoryTransitionException(StoryStatus current, StoryStatus target) {
        super("Story that has " + current + " status, can't transition to " + target);
    }
}
