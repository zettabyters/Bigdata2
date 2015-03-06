package org.RSSReader;
import java.net.URL;  
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import org.w3c.dom.Document;  
import org.w3c.dom.Element;  
//import org.w3c.dom.Node;  
import org.w3c.dom.NodeList;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
import java.io.*;
import java.lang.*;

  
public class RSSReader {  
  
   private static RSSReader instance = null;  
  
   private URL rssURL;  
  
   private RSSReader() {}  
  
   public static RSSReader getInstance() {  
      if (instance == null)  
         instance = new RSSReader();  
      return instance;  
   }  
  
   public void setURL(URL url) {  
      rssURL = url;  
   }  
  
   public void writeFeed() {  
      try {  
         DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();  
         Document doc = builder.parse(rssURL.openStream());  
  
         NodeList items = doc.getElementsByTagName("item");  
         
         // Write to file
//         String file = "\\home\\training\\narain\\amex\\rssinput.txt";
         
         String content1,content2,content3;
//         PrintWriter outputStream = new PrintWriter(file);

         BufferedWriter bw = new BufferedWriter(new FileWriter("/home/training/narain/amex/rssinput.txt"));
         int startIdx,endIdx;
         StringBuilder str = new StringBuilder();
         
         for (int i = 0; i < items.getLength(); i++) {  
            Element item = (Element)items.item(i);  
 //           System.out.println(getValue(item, "title"));  
 //           System.out.println(getValue(item, "description"));  
 //           System.out.println(getValue(item, "link"));  
 //           System.out.println();  
            
            content1 = getValue(item, "title");
            content2 = getValue(item, "description");
            content3 = getValue(item, "link");
            str.append(content2);
           // str = getValue(item, "description");
            startIdx = content2.indexOf("<img");
            endIdx = content2.indexOf("height=\"1\" width=\"1\" alt=\"\"/>");
            str.replace(++startIdx, endIdx, "");
            
            startIdx = 0;
            endIdx = str.indexOf(" - ");
            str.replace(startIdx, 3 + endIdx, "");
            
            startIdx = str.indexOf(".<");
            endIdx = str.indexOf("/>");
            str.replace(++startIdx, 2 + endIdx, "");
            
            
//            content2 = getValue(item, "description");
//            content3 = getValue(item, "link");

            System.out.println("content1" + content1);  
            System.out.println("content2" + content2);
            System.out.println("content3" + content3);
 //           System.out.println("content3" + content3);
            System.out.println();  
            
 //           bw.write(String.valueOf(i));
 //           bw.newLine();
            bw.write("link: " + content3);
            bw.newLine();
            bw.write("title: " + content1);
            bw.newLine();
            bw.write("description: " + str);
            
//            bw.newLine();
//            bw.write("link:" + content3);
            bw.newLine();
            
            content1 = "";
            content2 = "";
            content3 = "";
            str.setLength(0);
            //outputStream.println(content1);
            //outputStream.println(content2);
            //outputStream.println(content3);
            //outputStream.println();

         }
         
         bw.close();
         
      } catch (Exception e) {  
         e.printStackTrace();  
      }  
   }  
  
   public String getValue(Element parent, String nodeName) {  
      return parent.getElementsByTagName(nodeName).item(0).getFirstChild().getNodeValue();  
   }  
  
   public static void main(String[] args) {  
      try {  
         RSSReader reader = RSSReader.getInstance();  
//         reader.setURL(new URL("http://www.tullyrankin.com/feed/rss"));
//         http://www.ft.com/rss/home/us
           reader.setURL(new URL("http://feeds.reuters.com/reuters/businessNews"));
         reader.writeFeed();  
      } catch (Exception e) {  
         e.printStackTrace();  
      }  
   }  
}  