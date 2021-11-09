package baseq.UI;

import baseq.ArticleBase.Article;
import baseq.ArticleBase.ArticleBase;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class AuthorUI {

    public static void main(String[] args){
        AuthorUI author = new AuthorUI();
        author.showUI();

    }

    public AuthorUI(){
    }

    public void showUI(){
        ArticleBase.getArticleBase();
        Scanner sysIn = new Scanner(System.in);
        int userChoice;
        do {
            showAuthorOptions();
            userChoice = sysIn.nextInt();
            switch (userChoice){
                case 1->addArticle();
                case 2->editExistingArticle();
                case 3->showArticles();
                case 4->sendArticleToEditor();
            }

        }while(userChoice!=5);
    }

    private void showAuthorOptions(){
        System.out.println("---AUTHOR-MENU---\n" +
                "1-Dodaj artykuł\n" +
                "2-Edytuj istniejące lub niezgłoszone artykuły\n" +
                "3-Wyświet wszystkie artykuły\n" +
                "4-Zgłoś artykuł do dalszego przetwarzania\n" +
                "5-Wyjdź");
    }

    private void printMap(Map<Integer, Article> map){
        System.out.println("\n=============================");
        for(Map.Entry<Integer,Article> entry:map.entrySet()){
            System.out.println(entry.getKey()+": "+ entry.getValue());
        }
        System.out.println("=============================\n");
    }

    private void showAcceptedArticles(){
        ArticleBase.readFromFile();
        printMap(ArticleBase.getAvailableToEditArticles());
    }

    private void showUnReportedArticles(){
        ArticleBase.readFromFile();
        printMap(ArticleBase.getUnReportedArticles());
    }

    private void sendArticleToEditor(){

        int userChoice;
        Scanner sysIn = new Scanner(System.in);
        showUnReportedArticles();
        System.out.println("Który artykuł chcesz zgłosić do edycji");
        userChoice = sysIn.nextInt();
        try{
            ArticleBase.reportArticleToEditor(userChoice);
            ArticleBase.saveBaseToFile();

        }catch(Error | FileNotFoundException e){
            System.out.println(e);
        }


    }

    private void addArticle(){
        ArticleBase.readFromFile();
        String title;
        String authorName;
        String body;
        Scanner sysIn = new Scanner(System.in);
        System.out.println("Proszę podać tytuł");

        do{
            title=sysIn.nextLine();
        }while(title.length()<3);

        System.out.println("Proszę wprowadzić tekst artykułu");

        do{
            body= sysIn.nextLine();
        }while(body.length()<3);

        System.out.println("Proszę podać autora");

        do{
            authorName= sysIn.nextLine();
        }while(authorName.length()<3);

        ArticleBase.addArticle(new Article(title,body,authorName,"",false,false,false,false,false,false));
        try{
            ArticleBase.saveBaseToFile();
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

    private void editExistingArticle(){
        showAcceptedArticles();
        int userChoice;
        String editedText;
        Scanner sysIn = new Scanner(System.in);
        System.out.println("Podaj id artykułu który chcesz edytować:");
        userChoice=sysIn.nextInt();
        System.out.println("Podaj zedytowany tekst artykułu");
        if(sysIn.hasNext()){
            sysIn.nextLine();
        }
        editedText = sysIn.nextLine();
        try{
            ArticleBase.editArticle(userChoice,editedText);
            ArticleBase.saveBaseToFile();
        }catch (Error | FileNotFoundException e){
            System.out.println(e);
        }


    }

    private void showArticles() {
        ArticleBase.readFromFile();
        printMap(ArticleBase.getArticleBase());
    }
}
