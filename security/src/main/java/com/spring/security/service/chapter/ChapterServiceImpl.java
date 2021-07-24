package com.spring.security.service.chapter;

import com.spring.security.handle.exception.TdmException;
import com.spring.security.models.Chapter;
import com.spring.security.models.ChapterContent;
import com.spring.security.repository.ChapterRepository;
import com.spring.security.service.chaptercontent.ChapterContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    ChapterContentService chapterContentService;

    @Override
    public Chapter findById(Long id) throws TdmException {
        Chapter chapter = chapterRepository.findById(id).get();
        ChapterContent content = chapterContentService.findById(chapter.getChapterContentId()).get();
        chapter.setContent(content.getContent());
        return chapter;
    }
}
