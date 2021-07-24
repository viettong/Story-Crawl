package com.spring.security.service.crawl;

import com.spring.security.models.Chapter;
import com.spring.security.models.ChapterContent;
import com.spring.security.models.Story;
import com.spring.security.repository.ChapterContentRepository;
import com.spring.security.repository.ChapterRepository;
import com.spring.security.service.chaptercontent.ChapterContentService;
import io.jsonwebtoken.lang.Collections;
import org.apache.juli.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


@Service
public class TruyenFullCrawlServiceImpl implements CrawlService {


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

    @Override
    public List<String> getAllChapterInPage(String url) throws IOException {
        List<String> urls = new LinkedList<>();
        document = Jsoup.connect(url).get();
        Elements chapterUrl = document.select("div#list-chapter");
        Elements urlTag = chapterUrl.select("ul.list-chapter > li > a");
        for(Element e : urlTag){
            urls.add(e.attr("href"));
            System.out.println(e);
            System.out.println(e.attr("href"));
            System.out.println("---------------");
        }
        return urls;
    }

    String nextUrl = null;
    @Override
    public List<String> getAllPageUrl(String url) throws IOException {
        List<String> pageUrl = new LinkedList<>();
        document = Jsoup.connect(url).get();
        Elements pagination = document.select("input#total-page");
        int totalPage = parseInt(pagination.attr("value"));
        System.out.println(pagination.attr("value"));
        for(int i = 1 ;i <= totalPage; i++){
            pageUrl.add(url+"trang-"+i+"/");
        }
        return pageUrl;
    }

    @Override
    public void crawlAll(String url) throws IOException {
        List<String> urlsPage = getAllPageUrl(url);
        List<String> urlsChapter = new LinkedList<>();
        for(String urlPage : urlsPage){
            urlsChapter.addAll(getAllChapterInPage(urlPage));
        }
        List<String>divisionChapter1 = urlsChapter.subList(0,(int)urlsChapter.size()/2);
        List<String>divisionChapter2 = urlsChapter.subList((int)urlsChapter.size()/2,urlsChapter.size());

        Thread divThread1 = new Thread(){
            @Override
            public void run() {
                divisionChapter1.stream().forEach((pageUrl)-> {
                    try {
                        System.out.println(url);
                        crawlOneChapter(pageUrl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        };
        Thread divThread2 = new Thread(){
            @Override
            public void run() {
                divisionChapter2.stream().forEach((pageUrl)-> {
                    try {
                        System.out.println(url);
                        crawlOneChapter(pageUrl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        };
        divThread1.start();
        divThread2.start();

    }
}
