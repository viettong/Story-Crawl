package com.spring.security.DTO.Chapter;

import com.spring.security.DTO.GeneralDTO;
import com.spring.security.models.Chapter;
import com.spring.security.models.ChapterContent;
import lombok.Data;

import javax.persistence.*;

@Data
public class ChapterDTO {
    private Long Id;

    private Long createdBy;

    private Long createdTime;

    private Long modifiedBy;

    private Long modifiedTime;

    private boolean enable;

    private int chapNumber;

    private String title;

    private ChapterContentDTO chapterContentDTO;

    private Long storyId;

    public ChapterDTO(Chapter chapter){
        this.chapNumber = chapter.getChapNumber();
        this.title = chapter.getTitle();
        this.chapterContentDTO = new ChapterContentDTO(chapter.getChapterContent());
        this.storyId = chapter.getStoryId();
        this.createdBy = (chapter.getCreatedBy());
        this.createdTime = (chapter.getCreatedTime());
        this.Id =(chapter.getId());
        this.enable = (chapter.isEnable());
        this.modifiedBy = (chapter.getModifiedBy());
        this.modifiedTime = (chapter.getModifiedTime());
    }
}
