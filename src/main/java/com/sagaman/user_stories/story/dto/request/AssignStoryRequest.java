package com.sagaman.user_stories.story.dto.request;

import jakarta.validation.constraints.NotNull;

public class AssignStoryRequest {
    @NotNull
    private Long assigneeUserId;

    public Long getAssigneeUserId() {
        return assigneeUserId;
    }

    public void setAssigneeUserId(Long assigneeUserId) {
        this.assigneeUserId = assigneeUserId;
    }
}
