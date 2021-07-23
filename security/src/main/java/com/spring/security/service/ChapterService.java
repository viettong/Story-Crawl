package com.spring.security.service;

import com.spring.security.models.Chapter;

import java.util.Optional;
import java.util.UUID;

public interface ChapterService {
     Optional<Chapter> findById(UUID id);
}
