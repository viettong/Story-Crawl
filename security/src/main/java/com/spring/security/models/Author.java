package com.spring.security.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "author")
public class Author extends General{

    @Column(name = "name")
    private String name;

    @Column(name = "fb_url")
    private String fbUrl;

    @Column(name = "tag")
    private String tag;

    @Column(name = "stk")
    private String stk;

    @Column(name = "info_stk")
    private String infoStk;

    @Column(name = "user_id")
    private Long userId;

    @ManyToMany(mappedBy = "categories",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Story> stories;
    private Long story_id;

    public Author() {

    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Collection<Story> getStories() {
        return stories;
    }

    public void setStories(Collection<Story> stories) {
        this.stories = stories;
    }

    public Long getStory_id() {
        return story_id;
    }

    public void setStory_id(Long story_id) {
        this.story_id = story_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFbUrl() {
        return fbUrl;
    }

    public void setFbUrl(String fbUrl) {
        this.fbUrl = fbUrl;
    }

    public String getStk() {
        return stk;
    }

    public void setStk(String stk) {
        this.stk = stk;
    }

    public String getInfoStk() {
        return infoStk;
    }

    public void setInfoStk(String infoStk) {
        this.infoStk = infoStk;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Author(String name, String tag) {
        this.name = name;
        this.tag = tag;
    }

}
