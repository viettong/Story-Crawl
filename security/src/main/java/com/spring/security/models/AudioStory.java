package com.spring.security.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "truyen_mp3")
public class AudioStory extends General {

    @Column(name = "nguoi_doc")
    private String reader;

    @Column(name = "story_id")
    private UUID storyId;

    @Column(name = "phan")
    private String chapter;

    @Column(name = "mp3_url")
    private String mp3Url;

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public UUID getStoryId() {
        return storyId;
    }

    public void setStoryId(UUID storyId) {
        this.storyId = storyId;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }
}
