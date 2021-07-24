package com.spring.security.service.crawl;

import com.spring.security.models.Chapter;
import com.spring.security.models.ChapterContent;
import com.spring.security.models.Story;
import com.spring.security.repository.ChapterContentRepository;
import com.spring.security.repository.ChapterRepository;
import com.spring.security.service.chaptercontent.ChapterContentService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Document;

import java.io.IOException;

@Service
public class CrawlServiceImpl implements CrawlService {


    private static Document document = null;

    @Autowired
    ChapterContentRepository chapterContentRepository;

    @Autowired
    ChapterRepository chapterRepository;


    public static int parseInt(String number){
        return Integer.parseInt(number);
    }


    @Override
    public Chapter crawlOneChapter(String url) throws IOException {
//        Story story = new Story();
        Chapter chapter = new Chapter();
        ChapterContent chapterContent = new ChapterContent();
        document = Jsoup.connect(url).get();
        //Crawl tên
        Elements title = document.select("a.chapter-title");
        //Str "Chương 2: Khai quan"
        chapter.setTitle(title.text().split(":")[1]);
        chapter.setChapNumber(parseInt(title.text().split(":")[0].split(" ")[1]));

        Elements content = document.select("div#chapter-c");
        for(Element element : content.select("div")){
            element.remove();
        }
//        chapterContent.setContent((content.html()));
        chapterContent.setContent(Jsoup.clean(content.toString(),Whitelist.basic()));
        chapterContent = chapterContentRepository.save(chapterContent);
        chapter.setChapterContentId(chapterContent.getId());
        chapter = chapterRepository.save(chapter);
        chapter.setContent(chapterContent.getContent());

        return chapter;
    }
}
