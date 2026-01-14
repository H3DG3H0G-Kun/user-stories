package com.sagaman.user_stories.story.exception;

import java.util.UUID;

public class StoryNotFoundException extends RuntimeException{
    public StoryNotFoundException(UUID storyId){
        super("Story with id: " + storyId + " not found");
    }
}
