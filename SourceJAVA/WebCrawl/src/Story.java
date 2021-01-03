
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class Story {
    private String tenBoTruyen;
    private String tenTruyen;
    private String tacGia;
    private Boolean isFull; 
    List<Chapter> chapters;

    public String getTenBoTruyen() {
        return tenBoTruyen;
    }

    public void setTenBoTruyen(String tenBoTruyen) {
        this.tenBoTruyen = tenBoTruyen;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public Boolean getIsFull() {
        return isFull;
    }

    public void setIsFull(Boolean isFull) {
        this.isFull = isFull;
    }
    
    

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }
    public Story() {
        chapters = new ArrayList();
    }
    public void addChapter(Chapter c){
        chapters.add(c);
    }
    public Story(List<Chapter> chapters) {
        this.chapters = chapters;
    }
    
    
    
}
