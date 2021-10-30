package baseq;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ArticleBase {
        private static ArrayList<Article> articleBase;

        public static void addArticle(Article article){
            articleBase.add(article);
        }

        public static ArrayList<Article> getArticleBase(){
            return articleBase;
        }

        public static void acceptArticleByEditor(){}
        public static void acceptArticleByReviewer(){}



        public static void saveBaseToFile() throws FileNotFoundException {

            PrintWriter save = new PrintWriter("articleBase.txt");
            articleBase.forEach((item)->{
                save.println(item.getAuthor()+";"+item.getBody()+";"+item.getTitle()+";"+item.isAcceptedByEditor()+";"+item.isAcceptedByReviewer());
            });
            save.close();
        }

        public static void readFromFile() {
            articleBase= new ArrayList<>();

            File  file = new File("articleBase.txt");

            Scanner scanner = null;

            try{
                scanner = new Scanner(file);
                while(scanner.hasNext()){
                    String[] temp= scanner.nextLine().split(";");
                    articleBase.add(new Article(temp[0],temp[1],temp[2],temp[3]=="true"?true:false,temp[4]=="true"?true:false));
                }
            }catch(FileNotFoundException e){
                System.out.println(e);
            }

        }


}
