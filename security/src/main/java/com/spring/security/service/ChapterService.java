package com.spring.security.service;

import com.spring.security.models.Chapter;

import java.util.Optional;


public interface ChapterService {
     Optional<Chapter> findById(Long id);
}
