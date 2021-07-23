package com.spring.security.models;


import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "chapter")
public class Chapter extends General {

    @Column(name = "chapter_number")
    private int chapNumber;

    @Column(name = "title")
    private String title;

    @Column(name = "content",columnDefinition = "TEXT")
    private String content;

    @Column(name = "story_id")
    private UUID storyId;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
