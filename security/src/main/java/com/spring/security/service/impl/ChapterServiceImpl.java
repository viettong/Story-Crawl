package com.spring.security.service.impl;

import com.spring.security.models.Chapter;
import com.spring.security.repository.ChapterRepository;
import com.spring.security.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    ChapterRepository chapterRepository;

    @Override
    public Optional<Chapter> findById(UUID id) {
        return chapterRepository.findById(id);
    }
}
