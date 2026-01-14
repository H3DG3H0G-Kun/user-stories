package com.sagaman.user_stories.user.service;

import com.sagaman.user_stories.user.dto.request.UserRequest;
import com.sagaman.user_stories.user.dto.response.UserResponse;
import com.sagaman.user_stories.user.entity.User;
import com.sagaman.user_stories.user.exception.EmailAlreadyExistsException;
import com.sagaman.user_stories.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(user -> new UserResponse(user.getId(), user.getFullName(), user.getEmail(), user.getCreatedAt())).toList();
    }

    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id).map(user -> new UserResponse(user.getId(), user.getFullName(), user.getEmail(), user.getCreatedAt()));
    }

    @Transactional
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        User savedUser = userRepository.save(new User(request.getEmail(), request.getFullName()));
        return new UserResponse(savedUser.getId(), savedUser.getFullName(), savedUser.getEmail(), savedUser.getCreatedAt());
    }
}
