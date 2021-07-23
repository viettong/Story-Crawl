package com.spring.security.DTO.story;

import com.spring.security.DTO.Chapter.ChapterContentDTO;
import com.spring.security.DTO.Chapter.ChapterDTO;
import com.spring.security.models.Author;
import com.spring.security.models.Category;
import com.spring.security.models.Chapter;
import com.spring.security.models.Story;
import lombok.Data;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class StoryDTO {

    private Long Id;

    private Long createdBy;

    private Long createdTime;

    private Long modifiedBy;

    private Long modifiedTime;

    private boolean enable;


    private String name;

    private String thumbnail;

    private boolean license;

    private String shortDescription;

    public void setAuthors(Collection<Author> authors) {
        this.authors = authors;
    }

    private boolean translate;

    private Collection<Category> categories;

    private Collection<ChapterDTO> chapters;

    private Collection<Author> authors;

    public StoryDTO(Story story){
            this.createdBy = (story.getCreatedBy());
            this.createdTime = (story.getCreatedTime());
            this.Id =(story.getId());
            this.enable = (story.isEnable());
            this.modifiedBy = (story.getModifiedBy());
            this.modifiedTime = (story.getModifiedTime());
            this.enable = story.isEnable();
            this.name = story.getName();
            this.license = story.hasLicense();
            this.shortDescription = story.getShortDescription();
            this.authors = story.getAuthors();
            this.categories = getCategories();
            this.chapters = story.getChapters().stream().map(ChapterDTO::new).collect(Collectors.toList());
    }
}
