package baseq;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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

    public void showEditorOptions(){
        System.out.println("---EDITOR-MENU---\n" +
                "1-Wyświetl artykuły zgloszone przez autorów\n" +
                "2-Zgłoś artykuł do dalszego przetwarzania\n" +
                "3-Przeglądaj recenzje\n" +
                "4-Opublikuj artykuł\n" +
                "5-wyjdź");

    }


    public void printMap(Map<Integer,Article> map){
        System.out.println("\n=============================");
        for(Map.Entry<Integer,Article> entry:map.entrySet()){
            System.out.println(entry.getKey()+": "+ entry.getValue());
        }
        System.out.println("=============================\n");
    }

    public void showReportedArticles(){
        ArticleBase.readFromFile();
        printMap(ArticleBase.getReportedArticles());
    }

    public void sendArticleToReviewer(){
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

    public void showReviews(){
        ArticleBase.readFromFile();
        printMap(ArticleBase.getReviews());
    }

    public void publicArticle(){

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
