
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;



/**
 *
 * @author admin
 */
public class XML {
    private Story story;
    private DocumentBuilderFactory dbf ;
    private DocumentBuilder db;
    private String fileName ;
    private Document doc;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    
    
    
    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }
    
    public  XML(){
        
    }
    public void Instance(){
        try {
            if(this.dbf==null){
                 dbf = DocumentBuilderFactory.newInstance();
            }
            if(this.db==null){
                 db = dbf.newDocumentBuilder(); 
            }
            if(this.doc==null){
                 doc = db.parse(fileName);
            }     
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    

   
    
    
    public void save(Chapter c) {
        
        Instance();
        try {
            
            Element storys = doc.getDocumentElement();   
            Element chapter = doc.createElement("chapter");
            chapter.setTextContent(c.getContet());
            chapter.setAttribute("title",c.getTitle());
           
            storys.appendChild(chapter);
            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf = tff.newTransformer();   
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty("{https://www.w3.org/TR/xslt20/}indent-amount", "3");
           
            DOMSource source = new DOMSource(doc);   
            StreamResult sr = new StreamResult(fileName);
            tf.transform(source, sr);    
        } catch (DOMException | IllegalArgumentException | TransformerException ex) {
            System.out.println(ex);}          
    }
    public void createNew(String filename){
        
        try {                   
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder(); 
            doc = db.newDocument();
            Element storys = doc.createElement("story");
   
            doc.appendChild(storys);
            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf = tff.newTransformer();   
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.setOutputProperty("{https://www.w3.org/TR/xslt20/}indent-amount", "3");
           
            DOMSource source = new DOMSource(doc);   
            StreamResult sr = new StreamResult(filename);
            tf.transform(source, sr);    
        } catch (DOMException | IllegalArgumentException | TransformerException ex) {
            System.out.println(ex);} catch (ParserConfigurationException ex) {   
            Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
   
}
    

