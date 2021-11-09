package baseq.UI;

import baseq.ArticleBase.Article;
import baseq.ArticleBase.ArticleBase;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class ReviewerUI {

    public static void main(String[] args){
        ReviewerUI reviewer = new ReviewerUI();
        reviewer.showUI();
    }

    public ReviewerUI(){
    }

    public void showUI(){
        Scanner sysIn = new Scanner(System.in);
        int userChoice;
        do {
            showReviewerOptions();
            userChoice = sysIn.nextInt();
            switch (userChoice){
                case 1->addReview();
                case 2->showArticlesSentToReview();
                case 3->acceptArticle();

            }

        }while(userChoice!=4);
    }


    private void showReviewerOptions(){
        System.out.println("---REVIEWER-MENU---\n" +
                "1-Dodaj Receznję\n" +
                "2-Preglądaj artykuły zgłoszone do recenzji\n" +
                "3-Zatwierdź recenzję artykulu\n" +
                "4-Wyjdź");

    }


    private void printMap(Map<Integer, Article> map){
        System.out.println("\n=============================");
        for(Map.Entry<Integer,Article> entry:map.entrySet()){
            System.out.println(entry.getKey()+": "+ entry.getValue());
        }
        System.out.println("=============================\n");
    }



    private void showArticlesSentToReview(){
        ArticleBase.readFromFile();
        printMap(ArticleBase.getArticlesSentToReviewer());
    }

    private void acceptArticle(){
        int userChoice;
        Scanner sysIn = new Scanner(System.in);
        showArticlesSentToReview();
        System.out.println("który artykuł chcesz zatwierdzić?");
        userChoice = sysIn.nextInt();
        try{
            ArticleBase.acceptByReviewer(userChoice);
            ArticleBase.saveBaseToFile();
        }catch(Error | FileNotFoundException e){
            System.out.println(e);
        }
    }
    private void addReview(){
        String review;
        int userChoice;
        Scanner sysIn = new Scanner(System.in);
        showArticlesSentToReview();
        System.out.println("do którego artykułu chcesz dodać recenzję?");
        userChoice = sysIn.nextInt();
        System.out.println("podaj tekst recenzji");
        do{
            review=sysIn.nextLine();
        }while(review.length()<3);

        try{
            ArticleBase.addReview(userChoice,review);
            ArticleBase.saveBaseToFile();
        }catch (Error | FileNotFoundException e){
            System.out.println(e);
        }
    }
}