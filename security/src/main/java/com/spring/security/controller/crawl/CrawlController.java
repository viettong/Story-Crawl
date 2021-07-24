package com.spring.security.controller.crawl;

import com.spring.security.service.crawl.CrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CrawlController {

    @Autowired
    CrawlService crawlService;

    @GetMapping("/crawl")
    public void crawl(@RequestParam("url") String url) throws IOException {
        crawlService.crawlAll(url);
    }

}
