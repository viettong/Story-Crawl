package com.spring.security.service.crawl;

import com.spring.security.models.Chapter;

import java.io.IOException;

public interface CrawlService {
    public Chapter crawlOneChapter(String url) throws IOException;
}
