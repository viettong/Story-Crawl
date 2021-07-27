package com.spring.security.models;



import javax.persistence.*;

@Entity
@Table(name = "chapter")
public class Chapter extends General {

    @Column(name = "chapter_number")
    private int chapNumber;

    @Column(name = "title")
    private String title;

    private Long chapterContentId;

    @Transient
    private String content;

    @Column(name = "story_id")
    private Long storyId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

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

    public Long getChapterContentId() {
        return chapterContentId;
    }

    public void setChapterContentId(Long chapterContentId) {
        this.chapterContentId = chapterContentId;
    }

    public Long getStoryId() {
        return storyId;
    }

    public void setStoryId(Long storyId) {
        this.storyId = storyId;
    }


}
