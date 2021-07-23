package com.spring.security.models;


import javax.persistence.*;

@Entity
@Table(name = "chapter")
public class Chapter extends General {

    @Column(name = "chapter_number")
    private int chapNumber;

    @Column(name = "title")
    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id")
    private ChapterContent chapterContent;

    @Column(name = "story_id")
    private Long storyId;

    public int getChapNumber() {
        return chapNumber;
    }

    public void setChapNumber(int chapNumber) {
        this.chapNumber = chapNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ChapterContent getChapterContent() {
        return chapterContent;
    }

    public void setChapterContent(ChapterContent chapterContent) {
        this.chapterContent = chapterContent;
    }

    public Long getStoryId() {
        return storyId;
    }

    public void setStoryId(Long storyId) {
        this.storyId = storyId;
    }
}
