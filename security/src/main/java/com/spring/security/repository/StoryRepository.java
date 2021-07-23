package com.spring.security.repository;

import com.spring.security.models.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoryRepository extends JpaRepository<Story,Long> {

    @Query("SELECT s FROM Story s JOIN FETCH Chapter c ON s.Id = c.storyId where s.Id = :storyId")
    Optional<Story> findById(@Param("storyId") Long id);
}
