package com.spring.security.service.crawl;

import com.spring.security.Constant;
import com.spring.security.models.*;
import com.spring.security.repository.*;
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
import java.util.*;


@Service
public class TruyenFullCrawlServiceImpl implements CrawlService {


    private static Document document = null;


    //check () Quyển 1 - Chương 1-1: Nằm viện (1)
//    public static final String pattern = "(?![(])+\d+(?![)])".toString();
    @Autowired
    ChapterContentRepository chapterContentRepository;
    @Autowired
    ChapterRepository chapterRepository;
    @Autowired
    LogRepository logRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    StoryRepository storyRepository;

    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);

        } catch (Exception e) {
            return Constant.FAIL_CONVERT_CHAPTER_NUMBER;
        }
    }

    public int parseIntFromChapterTitle(String title) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String regex = "([(]\\d[)])";
            String standardTitle = title.split(regex)[0];
            String[] chapNum = standardTitle.split("\\D");
            for (String num : chapNum) {
                stringBuilder.append(num);
            }
            String numchap = stringBuilder.length() == 0 ? "-1" : stringBuilder.toString();
            return parseInt(numchap);
        } catch (Exception e) {
            Log log = new Log();
            log.setEnable(true);
            log.setDoiTuong("parseIntFromChapterTitle " + title);
            log.setAction("Crawl data + ex : " + e.toString());
            logRepository.save(log);
            return Constant.FAIL_CONVERT_CHAPTER_NUMBER;
        }

    }


    @Override
    public Chapter crawlOneChapter(String url, Long storyId) throws IOException {
        try {
            Chapter chapter = new Chapter();
            chapter.setEnable(true);
            ChapterContent chapterContent = new ChapterContent();
            document = Jsoup.connect(url).get();
            //Crawl tên
            Elements title = document.select("a.chapter-title");
            //Str "Chương 2: Khai quan"
            chapter.setTitle(title.text());
            if (chapterRepository.findByTitleAndStoryId(chapter.getTitle(), storyId).orElse(null) != null) return null;
            chapter.setChapNumber(parseIntFromChapterTitle(title.text()));
            chapter.setStoryId(storyId);
            Elements content = document.select("div#chapter-c");
            content.select("div").stream().forEach((c) -> c.remove());
            content.select("em").stream().forEach((c) -> c.remove());
            chapterContent.setContent(Jsoup.clean(content.toString(), Whitelist.basic()));
            chapterContent = chapterContentRepository.save(chapterContent);
            chapter.setChapterContentId(chapterContent.getId());
            chapter = chapterRepository.save(chapter);
            chapter.setContent(chapterContent.getContent());
            return chapter;
        } catch (Exception e) {
            Log log = new Log();
            log.setEnable(true);
            log.setDoiTuong("crawlOneChapter " + url);
            log.setAction("Crawl data + ex : " + e.toString());
            logRepository.save(log);
            return null;
        }
    }

    @Override
    public List<String> getAllChapterInPage(String url) throws IOException {
        List<String> urls = new LinkedList<>();
        try {
            document = Jsoup.connect(url).get();
            Elements chapterUrl = document.select("div#list-chapter");
            Elements urlTag = chapterUrl.select("ul.list-chapter > li > a");
            for (Element e : urlTag) {
                urls.add(e.attr("href"));
                System.out.println(e);
                System.out.println(e.attr("href"));
                System.out.println("---------------");
            }
        } catch (Exception e) {
            Log log = new Log();
            log.setEnable(true);
            log.setDoiTuong("getAllChapterInPage " + url);
            log.setAction("Crawl data + ex : " + e.toString());
            logRepository.save(log);
        }
        return urls;
    }

    String nextUrl = null;

    @Override
    public List<String> getAllPageUrl(String url) throws IOException {
        List<String> pageUrl = new LinkedList<>();
        try {
            document = Jsoup.connect(url).get();
            Elements pagination = document.select("input#total-page");
            int totalPage = parseInt(pagination.attr("value"));
            System.out.println(pagination.attr("value"));
            for (int i = 1; i <= totalPage; i++) {
                pageUrl.add(url + "trang-" + i + "/");
            }
        } catch (Exception ex) {
            Log log = new Log();
            log.setEnable(true);
            log.setDoiTuong("getAllPageUrl " + url);
            log.setAction("Crawl data + ex : " + ex.toString());
            logRepository.save(log);
        }
        return pageUrl;
    }

    @Override
    public void crawlAll(String url, Long storyId) throws IOException {

        List<String> urlsPage = getAllPageUrl(url);
        List<String> urlsChapter = new LinkedList<>();
        for (String urlPage : urlsPage) {
            urlsChapter.addAll(getAllChapterInPage(urlPage));
        }
//        List<String>divisionChapter1 = urlsChapter.subList(0,(int)urlsChapter.size()/2);
//        List<String>divisionChapter2 = urlsChapter.subList((int)urlsChapter.size()/2,urlsChapter.size());

        //

        List<String> divisionChapter1 = urlsChapter.subList(0, (int) urlsChapter.size() / 4);
        List<String> divisionChapter2 = urlsChapter.subList((int) urlsChapter.size() / 4, (int) urlsChapter.size() / 2);
        List<String> divisionChapter3 = urlsChapter.subList((int) urlsChapter.size() / 2, (int) (urlsChapter.size() * 3) / 4);
        List<String> divisionChapter4 = urlsChapter.subList((int) (urlsChapter.size() * 3) / 4, urlsChapter.size());
//
        Thread divThread1 = new Thread() {
            @Override
            public void run() {
                divisionChapter1.stream().forEach((pageUrl) -> {
                    try {
                        System.out.println(url);
                        crawlOneChapter(pageUrl, storyId);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        };
        Thread divThread2 = new Thread() {
            @Override
            public void run() {
                divisionChapter2.stream().forEach((pageUrl) -> {
                    try {
                        System.out.println(url);
                        crawlOneChapter(pageUrl, storyId);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        };
        Thread divThread3 = new Thread() {
            @Override
            public void run() {
                divisionChapter3.stream().forEach((pageUrl) -> {
                    try {
                        System.out.println(url);
                        crawlOneChapter(pageUrl, storyId);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        };
        Thread divThread4 = new Thread() {
            @Override
            public void run() {
                divisionChapter4.stream().forEach((pageUrl) -> {
                    try {
                        System.out.println(url);
                        crawlOneChapter(pageUrl, storyId);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        };
        //


//        Thread divThread1 = new Thread(){
//            @Override
//            public void run() {
//                divisionChapter1.stream().forEach((pageUrl)-> {
//                    try {
//                        System.out.println(url);
//                        crawlOneChapter(pageUrl,storyId);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
//            }
//        };
//        Thread divThread2 = new Thread(){
//            @Override
//            public void run() {
//                divisionChapter2.stream().forEach((pageUrl)-> {
//                    try {
//                        System.out.println(url);
//                        crawlOneChapter(pageUrl,storyId);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
//            }
//        };
        divThread1.start();
        divThread2.start();
        divThread3.start();
        divThread4.start();

    }

    @Override
    public void crawlStory(String url) throws IOException {
//        try {

        document = Jsoup.connect(url).get();
        Story story = new Story();
        Collection<Author> authors = new LinkedList<>();
        Collection<Category> categories = new LinkedList<>();

        Elements storyElement = document.select("div.info");
        Elements authorElements = storyElement.select("a[itemprop=author]");

        for (Element author : authorElements) {
            String[] tag = author.attr("href").split("/");
            Optional<Author> au = authorRepository.findAuthorByTag(tag[tag.length - 1]);

            if (au.orElse(null) == null) {
                authors.add(new Author(author.text(), tag[tag.length - 1]));
            } else {
                authors.add(au.get());
            }
        }

        Elements categoryElements = storyElement.select("a[itemprop=genre]");
        for (Element category : categoryElements) {
            String[] tag = category.attr("href").split("/");
            Optional<Category> ca = categoryRepository.findCategoryByCode(tag[tag.length - 1]);
            if (ca.orElse(null) == null) {
                categories.add(new Category(category.text(), tag[tag.length - 1]));
            } else {
                categories.add(ca.get());
            }
        }
        Elements sourceElement = storyElement.select("span.source");
        Element fullElement = storyElement.select("span.text-success").get(0);
        if (fullElement.text().equals("Full")) story.setFull(true);
        story.setAuthors(authors);
        story.setCategories(categories);
        story.setSource(sourceElement.text());

        Elements descriptionElements = document.select("div.desc-text");
        story.setName(document.select("h3.title").text());
        story.setShortDescription(Jsoup.clean(descriptionElements.toString(), Whitelist.basic()));
        Optional<Story> checkStory = storyRepository.findByName(story.getName());
        if (checkStory.orElse(null) != null) {

            crawlAll(url, checkStory.get().getId());
            return;
        }
        Story savedStory = storyRepository.save(story);

        crawlAll(url, savedStory.getId());


    }


}
