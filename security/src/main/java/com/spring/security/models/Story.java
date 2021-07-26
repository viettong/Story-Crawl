package com.spring.security.models;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "story")
public class Story extends General {
    @Column(name = "name")
    private String name;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "license")
    private boolean license;

    @Column(name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "is_full")
    private boolean full;

    @Column(name = "chapter_count")
    private int chapterCount;

    @Column(name = "source")
    private String source;

    @Column(name = "bien_dich")
    private String nguoiBienDich;


    @Column(name = "view_count")
    private String viewCount;

    @Column(name = "rate_star")
    private String rateStartEvg;

    @Column(name = "rate_count")
    private String rateCount;

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getRateStartEvg() {
        return rateStartEvg;
    }

    public void setRateStartEvg(String rateStartEvg) {
        this.rateStartEvg = rateStartEvg;
    }

    public String getRateCount() {
        return rateCount;
    }

    public void setRateCount(String rateCount) {
        this.rateCount = rateCount;
    }

    public boolean isLicense() {
        return license;
    }

    public int getChapterCount() {
        return chapterCount;
    }

    public void setChapterCount(int chapterCount) {
        this.chapterCount = chapterCount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getNguoiBienDich() {
        return nguoiBienDich;
    }

    public void setNguoiBienDich(String nguoiBienDich) {
        this.nguoiBienDich = nguoiBienDich;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Collection<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Collection<Author> authors) {
        this.authors = authors;
    }

    @Column(name = "translate")
    private boolean translate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "story_category",
            joinColumns = @JoinColumn(name = "story_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Collection<Category> categories;

    @OneToMany(mappedBy = "storyId", fetch = FetchType.LAZY, orphanRemoval = true)
    private Collection<Chapter> chapters;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "story_author",
            joinColumns = @JoinColumn(name = "story_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Collection<Author> authors;

    public Collection<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Collection<Chapter> chapters) {
        this.chapters = chapters;
    }

    public boolean hasLicense() {
        return license;
    }

    public void setLicense(boolean license) {
        this.license = license;
    }

    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public boolean isTranslate() {
        return translate;
    }

    public void setTranslate(boolean translate) {
        this.translate = translate;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }
}
