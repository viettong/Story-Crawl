package com.spring.security.service.story;

import com.spring.security.models.Story;
import com.spring.security.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    StoryRepository storyRepository;

    @Override
    public Optional<Story> findById(Long id) {
        return storyRepository.findById(id);
    }
}
