package com.sagaman.user_stories.user.dto.response;

import java.time.LocalDate;

public class UserResponse {
    private final Long id;

    private final String email;

    private final String fullName;

    private final LocalDate createdAt;

    public UserResponse(Long id, String fullName, String email, LocalDate createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

}
