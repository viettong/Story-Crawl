import java.io.File;
import java.io.IOException;
import javax.swing.JTextArea;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class ChapterCollect {
    private XML xml ;
    
    public ChapterCollect(){
        xml = new XML();
    }
    public void getAllChapter(String url,JTextArea txa){
            Story story = new Story();
            
            try {         
            Document doc = Jsoup.connect(url).get();
            String chapOneUrl = doc.select("div#dsc > ul > li > a").first().attr("href");  
                try {
                     Elements thongTin = doc.select("div.thong_tin > p");
                    story.setTacGia(thongTin.get(0).select("span").last().text());          
                    if(thongTin.get(1).select("span").last().text().equals("Hoàn Thành")){
                        story.setIsFull(true);
                    }
                    else
                    {
                        story.setIsFull(false);
                    }
                    story.setTenTruyen(doc.select("h1").text()); 
                    story.setTenBoTruyen(thongTin.get(2).select("a").text());
                } catch (Exception e) {
                }
           
           
            String[] fileName = url.split("/");
            String name =fileName[fileName.length-1];
            xml.setFileName(name+".xml");
            
            File  f = new File(xml.getFileName());
            if(!f.exists()){
                xml.createNew(xml.getFileName());
            }
            xml.Instance();
            xml.setStory(story);
            Chapter chapter0 = new Chapter();
            chapter0.setTitle(story.getTenTruyen());
            chapter0.setContet(story.getTenBoTruyen()+"-"+story.getTacGia()+"-"+story.getIsFull());
            xml.save(chapter0);
            chapter0 =null;
            Chapter chapter = getOneChapter(chapOneUrl,false,false);
            xml.save(chapter);
            int i = 1;
            while(chapter.getNextUrl()!=null) { 
                String urlNext = chapter.getNextUrl();     
                chapter = getOneChapter(urlNext,false,false);  
               
                
                txa.append("chap : "+ i++ + "\n");
                txa.setCaretPosition(txa.getDocument().getLength());
            }       
            
            txa.append("Process Of Write");
        } catch (IOException ex) {
            
        }
       
        
    }
    
    public Chapter getOneChapter(String url,boolean initOne,boolean initEndToEnd){
        try {
            if(initOne){
                String[] fileName = url.split("/");
                String name = fileName[fileName.length - 1];
                xml.setFileName(name + ".xml");

                File f = new File(xml.getFileName());
                if (!f.exists()) {
                    xml.createNew(xml.getFileName());
                }
                xml.Instance();
                
            }
            Document doc = Jsoup.connect(url).get();
            Elements title = doc.select("h2");
            Elements tenTruyen = doc.select("h1");
            Elements content = doc.select("div.reading");  
            String text = Jsoup.clean(content.html(),Whitelist.basic());         
            Chapter chapter = new Chapter();
            Elements check = doc.select("span.pull-right > a");  
            
            if(check.size()!=0){
                chapter.setNextUrl(check.attr("href"));   
              
            }
            if(initEndToEnd){
                 check = doc.select("div.link-pages-number > a >span.next");  
                 if(check.size()!=0){
                     check = doc.select("div.link-pages-number > a"); 
                     System.out.println(check.size());
                     chapter.setNextUrl(check.get(check.size()-1).attr("href"));   
                 }
            }
            chapter.setTitle(title.text());
            chapter.setContet(text); 
            xml.save(chapter);
            return chapter;
//            FileWriter myWriter = new FileWriter("filename.txt");
//            myWriter.write(content.toString());
//            myWriter.close(); 
            
        } catch (IOException ex) {
            System.out.println(ex+"exChapterCollect");
        }
        return null;
    }
    
    
    public void startWithPage1(String url,JTextArea txa){
            Story story = new Story();
            
            try {         
            Document doc = Jsoup.connect(url).get();
            String chapOneUrl = url;
                try {
                     Elements thongTin = doc.select("div.thong_tin > p");
                    story.setTacGia(thongTin.get(0).select("span").last().text());          
                    if(thongTin.get(1).select("span").last().text().equals("Hoàn Thành")){
                        story.setIsFull(true);
                    }
                    else
                    {
                        story.setIsFull(false);
                    }
                    story.setTenTruyen(doc.select("h1").text()); 
                    story.setTenBoTruyen(thongTin.get(2).select("a").text());
                } catch (Exception e) {
                }
           
           
            String[] fileName = url.split("/");
            String name =fileName[fileName.length-1];
            xml.setFileName(name+".xml");
            
            File  f = new File(xml.getFileName());
            if(!f.exists()){
                xml.createNew(xml.getFileName());
            }
            xml.Instance();
            xml.setStory(story);
            Chapter chapter0 = new Chapter();
            chapter0.setTitle(story.getTenTruyen());
            chapter0.setContet(story.getTenBoTruyen()+"-"+story.getTacGia()+"-"+story.getIsFull());
            xml.save(chapter0);
            chapter0 =null;
            Chapter chapter = getOneChapter(chapOneUrl,false,true);
            xml.save(chapter);
            int i = 1;
            while(chapter.getNextUrl()!=null) { 
                String urlNext = chapter.getNextUrl();     
                chapter = getOneChapter(urlNext,false,true);  
               
                
                txa.append("chap : "+ i++ + "\n");
                txa.setCaretPosition(txa.getDocument().getLength());
            }       
            
            txa.append("Process Of Write");
        } catch (IOException ex) {
            
        }
       
        
    }
    
}
