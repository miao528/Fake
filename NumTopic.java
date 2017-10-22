import java.util.*; 
import java.io.*;
import java.lang.*;

public class NumTopic {
   
   public static void main(String[] args){
      System.out.println("Please type in the topic you would like to read.");
      Scanner sc = new Scanner(System.in);
      String topic = sc.next();
      List<String> wanted = getNews(10, topic);
      /*
      System.out.print(wanted.size());
      for(int i = 0; i < 10; i ++) {
         System.out.println(wanted.get(i));
      }
      */
   }

   // pre : take an int and a string as topic
   // post: search and get n articles revelant to topic
   public static List<String> getNews(int n, String topic) {
      // the original all articles
      File folder = new File("https://drive.google.com/open?id=0BwmD_VLjROrfTHk4NFg2SndKcjQ"); // path of stories
      File[] listOfFiles = folder.listFiles();
      //result List<String>
      List<String> result = new ArrayList<String>();
      Random rand = new Random();
      // number of revelant data collected
      int limit = 0;
      
      // 
      while (result.size() < n && limit <= 500) {
         limit ++;
         int randfile = rand.nextInt(listOfFiles.length);
         // randomly choose a file from listOfFiles
         try {
            Scanner article = new Scanner(listOfFiles[randfile]);
            boolean notMatch = true;
            String articleString = "";
            
            while (article.hasNext()) {
               String line = article.next();
               articleString = articleString + " " + line;
               
               // notMatch become false if there is a relevant word
               if(notMatch) {
                  notMatch = !line.toLowerCase().contains(topic.toLowerCase());
               }
            }
            // if there is a match, the article is added as a string to the String List
            if(!notMatch) {
               result.add(articleString);
            }
         } catch (FileNotFoundException e) {
            // files are choosen so they are certainly found
         }
      }
      return result;
   }
}