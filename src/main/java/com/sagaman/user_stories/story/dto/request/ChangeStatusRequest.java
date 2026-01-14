package com.sagaman.user_stories.story.dto.request;

import com.sagaman.user_stories.story.enums.StoryStatus;
import jakarta.validation.constraints.NotNull;

public class ChangeStatusRequest {
    @NotNull
    private StoryStatus newStatus;

    public StoryStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(StoryStatus newStatus) {
        this.newStatus = newStatus;
    }
}
