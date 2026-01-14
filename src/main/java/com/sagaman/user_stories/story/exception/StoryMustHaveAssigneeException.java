package com.sagaman.user_stories.story.exception;

public class StoryMustHaveAssigneeException extends RuntimeException {
    public StoryMustHaveAssigneeException(){
        super("Story must have assignee!");
    }
}
