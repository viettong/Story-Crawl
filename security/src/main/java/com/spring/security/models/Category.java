package com.spring.security.models;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "category")
public class Category extends General {

    @Column(name ="name")
    private String name;

    @Column(name = "code")
    private String code;

    @ManyToMany(mappedBy = "categories",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Story> stories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Collection<Story> getStories() {
        return stories;
    }

    public void setStories(Collection<Story> stories) {
       this.stories = stories;
    }
}
