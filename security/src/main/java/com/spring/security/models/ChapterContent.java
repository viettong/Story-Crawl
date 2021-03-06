package com.spring.security.models;

import javax.persistence.*;

@Entity
@Table(name = "chapter_content")
public class ChapterContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "content",columnDefinition = "TEXT")
    private String content;

    @OneToOne(mappedBy = "chapterContent",fetch = FetchType.LAZY)
    private Chapter chapter;

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
