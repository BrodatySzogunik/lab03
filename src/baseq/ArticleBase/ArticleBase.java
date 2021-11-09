package baseq.ArticleBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArticleBase {

        private static Map<Integer,Article> articleBase;

        public static void addArticle(Article article){
            articleBase.put(articleBase.keySet().size()+1,article);
        }

        public static Map<Integer,Article> getArticleBase(){
            return articleBase;
        }


        public static Map<Integer,Article> getAvailableToEditArticles(){ // artykuły dostępne do edytowania dla autora
            Map<Integer,Article> result =  articleBase.entrySet().stream()
                    .filter(item->(item.getValue().isAcceptedByReviewer()&&item.getValue().isAcceptedByEditor())||(!item.getValue().isSentToEditor()))
                    .collect(Collectors.toMap(x->x.getKey(), x-> x.getValue()));


            System.out.println(result);
            return result;
        }

        public static Map<Integer,Article> getUnReportedArticles(){ // artykuly nie zgłoszone do edytora
            Map<Integer,Article> result =  articleBase.entrySet().stream()
                    .filter(item->!item.getValue().isSentToEditor())
                    .collect(Collectors.toMap(x->x.getKey(), x-> x.getValue()));
            System.out.println(result);
            return result;
        }

        public static Map<Integer,Article> getReportedArticles(){ //artykuły zgłoszone do edytora
            Map<Integer,Article> result =  articleBase.entrySet().stream()
                    .filter(item->(item.getValue().isSentToEditor()&&!(item.getValue().isSentToReviewer())))
                    .collect(Collectors.toMap(x->x.getKey(), x-> x.getValue()));


            System.out.println(result);
            return result;
        }

        public static Map<Integer,Article> getReviews(){ //artykuly zaakceptowane/zrecenzowane
            Map<Integer,Article> result =  articleBase.entrySet().stream()
                    .filter(item->(item.getValue().isSentToReviewer())&&(item.getValue().isAcceptedByReviewer()))
                    .collect(Collectors.toMap(x->x.getKey(), x-> x.getValue()));


            System.out.println(result);
            return result;
        }

        public static Map<Integer,Article> getArticlesSentToReviewer(){ // artykuły zgłoszone do recenzenta
            Map<Integer,Article> result =  articleBase.entrySet().stream()
                    .filter(item->(item.getValue().isSentToReviewer())&&!(item.getValue().isAcceptedByReviewer()))
                    .collect(Collectors.toMap(x->x.getKey(), x-> x.getValue()));


            System.out.println(result);
            return result;
        }
        public static void addReview(int id, String review){// dodawanie recenzji
            if(articleBase.get(id).isSentToEditor()){
                if((articleBase.get(id).isSentToReviewer())&&!(articleBase.get(id).isAcceptedByEditor())){
                    articleBase.get(id).setReview(review);
                }else{
                    throw new Error("artykuł nie zostal wysłany do recenzji lub jest już opublikowany");
                }
            }else{
                throw new Error("artykul nie został zgłoszony do edytora");
            }
        }


        public static void editArticle(int id, String editedText){
            if((articleBase.get(id).isAcceptedByEditor()&&articleBase.get(id).isAcceptedByReviewer())||(!articleBase.get(id).isSentToEditor())){
                articleBase.get(id).setBody(editedText);
                articleBase.get(id).setEdited(true);
                articleBase.get(id).setAcceptedByEditor(false);
                articleBase.get(id).setAcceptedByReviewer(false);
                articleBase.get(id).setSentToEditor(false);
            }else{
                throw new Error("można edytować tylko opublikowane lub nie zgłoszone artykuly");
            }

        }

        public static void reportArticleToEditor(int id){
            if(!articleBase.get(id).isSentToEditor()){
                articleBase.get(id).setSentToEditor(true);
            }else{
                throw new Error("artykół o tym indeksie został już zgłoszony lub nie istnieje");
            }


        }

        public static void reportArticleToReviever(int id){
            if(!articleBase.get(id).isSentToReviewer()&(articleBase.get(id).isSentToEditor())){
                articleBase.get(id).setSentToReviewer(true);
            }else{
                throw new Error("artykół o tym indeksie został już zgłoszony lub nie istnieje");
            }

        }

        public static void acceptByEditor (int id){
            if(articleBase.get(id).isAcceptedByReviewer()){
                if(!articleBase.get(id).isAcceptedByEditor()){
                    articleBase.get(id).setAcceptedByEditor(true);
                }else{
                    throw new Error("artykół o tym indeksie został już opublikowany");
                }
            }else{
                throw new Error("artykuł nie jest zaakceptowany przez recenzenta");
            }

        }
        public static void acceptByReviewer(int id){
            if(articleBase.get(id).isSentToReviewer()) {
                if (!articleBase.get(id).isAcceptedByReviewer()) {
                    articleBase.get(id).setAcceptedByReviewer(true);
                } else {
                    throw new Error("artykuł został już zaakceptowany");
                }
            }else{
                throw new Error("artykuł nie został zgłoszony do recenzji");
            }
        }

        public static void saveBaseToFile() throws FileNotFoundException {

            PrintWriter save = new PrintWriter("articleBase.txt");
            for(Map.Entry<Integer,Article> entry:articleBase.entrySet()){
                save.println(entry.getKey()+";"+entry.getValue().getTitle()+";"+entry.getValue().getBody()+";"+entry.getValue().getAuthor()+";"+entry.getValue().getReview()+";"+entry.getValue().isAcceptedByEditor()+";"+entry.getValue().isAcceptedByReviewer()+";"+entry.getValue().isEdited()+";"+entry.getValue().isSentToEditor()+";"+entry.getValue().isSentToReviewer()+";"+entry.getValue().isRejected());

            }
            save.close();
        }

        public static void readFromFile() {
            articleBase= new HashMap<>();

            File  file = new File("articleBase.txt");

            Scanner scanner = null;

            try{
                scanner = new Scanner(file);
                while(scanner.hasNext()){
                    String[] temp= scanner.nextLine().split(";");
                    articleBase.put(Integer.parseInt(temp[0]),new Article(temp[1]
                            ,temp[2]
                            ,temp[3]
                            ,temp[4]
                            ,temp[5].equals("true")?true:false
                            ,temp[6].equals("true")?true:false
                            ,temp[7].equals("true")?true:false
                            ,temp[8].equals("true")?true:false
                            ,temp[9].equals("true")?true:false
                            ,temp[10].equals("true")?true:false));
                }
            }catch(FileNotFoundException e){
                System.out.println(e);
            }

        }


}
