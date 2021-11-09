package baseq.UI;

import baseq.ArticleBase.Article;
import baseq.ArticleBase.ArticleBase;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class EditorUI {


    public static void main(String[] args){
        EditorUI editor = new EditorUI();
        editor.showUI();
    }

    public EditorUI(){

    }


    public void showUI(){
        Scanner sysIn = new Scanner(System.in);
        int userChoice;
        do {
            showEditorOptions();
            userChoice = sysIn.nextInt();
            switch (userChoice){
                case 1->showReportedArticles();
                case 2->sendArticleToReviewer();
                case 3->showReviews();
                case 4->publicArticle();
            }

        }while(userChoice!=5);
    }

    private void showEditorOptions(){
        System.out.println("---EDITOR-MENU---\n" +
                "1-Wyświetl artykuły zgloszone przez autorów\n" +
                "2-Zgłoś artykuł do dalszego przetwarzania\n" +
                "3-Przeglądaj recenzje\n" +
                "4-Opublikuj artykuł\n" +
                "5-wyjdź");

    }


    private void printMap(Map<Integer, Article> map){
        System.out.println("\n=============================");
        for(Map.Entry<Integer,Article> entry:map.entrySet()){
            System.out.println(entry.getKey()+": "+ entry.getValue());
        }
        System.out.println("=============================\n");
    }

    private void showReportedArticles(){
        ArticleBase.readFromFile();
        printMap(ArticleBase.getReportedArticles());
    }

    private void sendArticleToReviewer(){
        int userChoice;
        Scanner sysIn = new Scanner(System.in);
        showReportedArticles();
        System.out.println("Który artykuł chcesz zgłosić do recenzji");
        userChoice = sysIn.nextInt();
        try{
            ArticleBase.reportArticleToReviever(userChoice);
            ArticleBase.saveBaseToFile();
        }catch(Error | FileNotFoundException e){
            System.out.println(e);
        }
    }

    private void showReviews(){
        ArticleBase.readFromFile();
        printMap(ArticleBase.getReviews());
    }

    private void publicArticle(){

        int userChoice;
        Scanner sysIn = new Scanner(System.in);
        showReviews();
        System.out.println("Który artykuł chcesz opublikować?");
        userChoice = sysIn.nextInt();
        try{
            ArticleBase.acceptByEditor(userChoice);
            ArticleBase.saveBaseToFile();
        }catch(Error | FileNotFoundException e){
            System.out.println(e);

        }
    }




}
