package com.sagaman.user_stories.story.controller;

import com.sagaman.user_stories.story.dto.request.AssignStoryRequest;
import com.sagaman.user_stories.story.dto.request.ChangeStatusRequest;
import com.sagaman.user_stories.story.dto.request.CreateCommentRequest;
import com.sagaman.user_stories.story.dto.request.CreateStoryRequest;
import com.sagaman.user_stories.story.dto.response.CommentResponse;
import com.sagaman.user_stories.story.dto.response.StoryResponse;
import com.sagaman.user_stories.story.service.StoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
public class StoryController {
    private final StoryService storyService;


    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping("/stories")
    public ResponseEntity<List<StoryResponse>> getAllStories(){
        return ResponseEntity.ok(storyService.getAllStories());
    }

    @GetMapping("/stories/{id}")
    public ResponseEntity<StoryResponse> getStoryById(@PathVariable UUID id){
        return storyService.getStoryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/stories")
    public ResponseEntity<?> createStory(@RequestBody @Valid CreateStoryRequest request){
        StoryResponse response = storyService.createStory(request);

        return ResponseEntity
                .created(URI.create("/stories/" + response.getId()))
                .body(response);
    }

    @PatchMapping("/stories/{id}/assign")
    public ResponseEntity<StoryResponse> assignStory(@PathVariable UUID id, @Valid @RequestBody AssignStoryRequest request) {
        return ResponseEntity.ok(storyService.assignStory(id, request.getAssigneeUserId()));
    }

    @PatchMapping("/stories/{id}/status")
    public ResponseEntity<StoryResponse> changeStoryStatus(@PathVariable UUID id, @Valid @RequestBody ChangeStatusRequest request) {
        return ResponseEntity.ok(storyService.changeStoryStatus(id, request.getNewStatus()));
    }

    @GetMapping("/stories/{id}/comments")
    public ResponseEntity<List<CommentResponse>> getCommentsByStoryId(@PathVariable UUID id) {
        return ResponseEntity.ok(storyService.getCommentsByStoryId(id));
    }

    @PostMapping("/stories/{id}/comments")
    public ResponseEntity<?> addCommentToStory(@PathVariable UUID id, @RequestBody @Valid CreateCommentRequest request) {
        CommentResponse response = storyService.addCommentToStory(request.getComment(), request.getAuthorId(), id);

        return ResponseEntity
                .created(URI.create("/stories/" + id + "/comments/" + response.getId()))
                .body(response);
    }

}
