package com.spring.security.service.crawl;

import com.spring.security.models.Chapter;
import com.spring.security.models.Story;

import java.io.IOException;
import java.util.List;

public interface CrawlService {
    public Chapter crawlOneChapter(String url) throws IOException;

    public List<String> getAllChapterInPage(String url) throws  IOException;

    public List<String> getAllPageUrl(String url) throws  IOException;

    public void crawlAll(String url) throws IOException;
}
