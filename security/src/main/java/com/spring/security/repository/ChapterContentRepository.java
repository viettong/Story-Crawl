package com.spring.security.repository;

import com.spring.security.models.ChapterContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChapterContentRepository extends JpaRepository<ChapterContent,Long> {
    public Optional<ChapterContent> findById(Long id);
}
