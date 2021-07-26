package com.spring.security.controller.crawl;

import com.spring.security.service.crawl.CrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CrawlController {

    @Autowired
    CrawlService crawlService;

    @GetMapping("/crawl-story")
    public ResponseEntity<?> crawl(@RequestParam("url") String url) throws IOException, CloneNotSupportedException {
        crawlService.crawlStory(url);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
