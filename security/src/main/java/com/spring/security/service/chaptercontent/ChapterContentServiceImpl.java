package com.spring.security.service.chaptercontent;


import com.spring.security.models.ChapterContent;
import com.spring.security.repository.ChapterContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChapterContentServiceImpl implements ChapterContentService {
    @Autowired
    ChapterContentRepository chapterContentRepository;
    @Override
    public Optional<ChapterContent> findById(Long id) {
        return chapterContentRepository.findById(id);
    }
}
