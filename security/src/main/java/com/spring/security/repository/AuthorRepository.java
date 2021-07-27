package com.spring.security.repository;

import com.spring.security.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    public Optional<Author> findAuthorByTag(String tag);
}
