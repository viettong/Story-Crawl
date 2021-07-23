package com.spring.security.DTO.Chapter;

import com.spring.security.models.ChapterContent;
import lombok.Data;

import javax.persistence.Column;

@Data
public class ChapterContentDTO {
    private Long id;

    private String content;

    public ChapterContentDTO(ChapterContent chapterContent){
        this.id = chapterContent.getId();
        this.content = chapterContent.getContent();
    }
}
