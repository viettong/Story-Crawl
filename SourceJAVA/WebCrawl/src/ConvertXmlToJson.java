
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class ConvertXmlToJson {
    
    //private String fileName = "story.xml";
   
    
    public static  void convert(String fileName){
        try {
            File f = new File(fileName);
            Scanner sc = new Scanner(f);
            StringBuilder builder= new StringBuilder("");
            while(sc.hasNextLine()){
            builder.append(sc.nextLine().replaceAll("&lt;br&gt;", "<br>"));
            }
            System.out.println(builder.toString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConvertXmlToJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
