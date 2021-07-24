package com.spring.security.service.chaptercontent;

import com.spring.security.models.ChapterContent;

import java.util.Optional;

public interface ChapterContentService {
    Optional<ChapterContent>findById(Long id);
}
