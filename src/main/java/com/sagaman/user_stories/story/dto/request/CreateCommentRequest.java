package com.sagaman.user_stories.story.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateCommentRequest {

    @Size(max = 2000)
    @NotBlank
    private String comment;

    @NotNull
    private Long authorId;

    public @Size(max = 2000) @NotBlank String getComment() {
        return comment;
    }

    public void setComment(@Size(max = 2000) @NotBlank String comment) {
        this.comment = comment;
    }

    public @NotNull Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(@NotNull Long authorId) {
        this.authorId = authorId;
    }
}
