package com.sagaman.user_stories.story.repository;

import com.sagaman.user_stories.story.entity.StoryComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<StoryComment, UUID> {
    List<StoryComment> findByStoryIdOrderByCreatedAtAsc(UUID storyId);

}
