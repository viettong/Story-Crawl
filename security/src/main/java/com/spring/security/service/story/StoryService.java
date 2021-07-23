package com.spring.security.service.story;

import com.spring.security.models.Story;

import java.util.Optional;

public interface StoryService {
    Optional<Story> findById(Long id);
}
