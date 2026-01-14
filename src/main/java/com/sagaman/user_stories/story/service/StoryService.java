package com.sagaman.user_stories.story.service;

import com.sagaman.user_stories.story.dto.request.CreateStoryRequest;
import com.sagaman.user_stories.story.dto.response.CommentResponse;
import com.sagaman.user_stories.story.dto.response.StoryResponse;
import com.sagaman.user_stories.story.entity.Story;
import com.sagaman.user_stories.story.entity.StoryComment;
import com.sagaman.user_stories.story.enums.StoryStatus;
import com.sagaman.user_stories.story.exception.*;
import com.sagaman.user_stories.story.repository.CommentRepository;
import com.sagaman.user_stories.story.repository.StoryRepository;
import com.sagaman.user_stories.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sagaman.user_stories.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.sagaman.user_stories.story.enums.StoryStatus.IN_PROGRESS;

@Service
public class StoryService {
    private final StoryRepository storyRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public StoryService(StoryRepository storyRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.storyRepository = storyRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public StoryResponse createStory(CreateStoryRequest request) {
        Story savedStory = storyRepository.save(new Story(request.getTitle(), request.getDescription()));
        return toResponse(savedStory);
    }

    public List<StoryResponse> getAllStories() {
        return storyRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<StoryResponse> getStoryById(UUID id) {
        return storyRepository.findById(id).map(this::toResponse);
    }

    @Transactional
    public StoryResponse assignStory(UUID id, Long assigneeId) {
        Story story = storyRepository.findById(id).orElseThrow(() -> new StoryNotFoundException(id));

        if (story.getStatus() == StoryStatus.DONE ||
                story.getStatus() == StoryStatus.CANCELLED) {
            throw new StoryAlreadyFinishedException(story.getStatus());
        }

        User assignee = userRepository.findById(assigneeId).orElseThrow(() -> new UserNotFoundException(assigneeId));

        story.assignTo(assignee);

        return toResponse(story);
    }

    @Transactional
    public StoryResponse changeStoryStatus(UUID id, StoryStatus newStatus) {
        Story story = storyRepository.findById(id).orElseThrow(() -> new StoryNotFoundException(id));

        if (!story.getStatus().canTransitionTo(newStatus)) {
            throw new InvalidStoryTransitionException(story.getStatus(), newStatus);
        }

        if (newStatus == IN_PROGRESS && story.getAssignee() == null) {
            throw new StoryMustHaveAssigneeException();
        }

        story.changeStatusTo(newStatus);

        return toResponse(story);
    }

    @Transactional
    public CommentResponse addCommentToStory(String comment, Long authorId, UUID storyId) {
        Story story = storyRepository.findById(storyId).orElseThrow(() -> new StoryNotFoundException(storyId));
        User author = userRepository.findById(authorId).orElseThrow(() -> new UserNotFoundException(authorId));

        if (!story.getStatus().canBeCommentedOn()) {
            throw new CanNotBeCommentedException(story.getStatus());
        }

        StoryComment savedComment = commentRepository.save(new StoryComment(story, author, comment));
        return toCommentResponse(savedComment);
    }

    private StoryResponse toResponse(Story story) {
        return new StoryResponse(
                story.getId(),
                story.getTitle(),
                story.getDescription(),
                story.getStatus(),
                story.getAssignee() != null ? story.getAssignee().getId() : null,
                story.getCreatedAt(),
                story.getUpdatedAt(),
                story.getVersion()
        );
    }

    private CommentResponse toCommentResponse(StoryComment comment){
        return new CommentResponse(comment.getId(), comment.getStory().getId(), comment.getAuthor().getId(), comment.getMessage());
    }

    public List<CommentResponse> getCommentsByStoryId(UUID storyId) {
        storyRepository.findById(storyId)
                .orElseThrow(() -> new StoryNotFoundException(storyId));

        return commentRepository
                .findByStoryIdOrderByCreatedAtAsc(storyId)
                .stream()
                .map(this::toCommentResponse)
                .toList();
    }

}
