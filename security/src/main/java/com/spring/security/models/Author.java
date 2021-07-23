package com.spring.security.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "author")
public class Author extends General {

    @Column(name = "name")
    private String name;

    @Column(name = "fb_url")
    private String fbUrl;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "stk")
    private String stk;

    @Column(name = "info_stk")
    private String infoStk;

    @Column(name = "user_id")
    private UUID userId;

    @ManyToMany(mappedBy = "categories",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Story> stories;
    private UUID story_id;


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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
