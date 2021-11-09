package baseq.UI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        AuthorUI author= new AuthorUI();
        EditorUI editor = new EditorUI();
        ReviewerUI reviewer = new ReviewerUI();
        int userChoice;
        Scanner sysIn = new Scanner(System.in);

        do{
            System.out.println("---MAIN-MENU---\n" +
                    "1-Author menu\n" +
                    "2-Editor menu\n" +
                    "3-Reviewer menu\n" +
                    "4-Exit");
            userChoice =sysIn.nextInt();
            switch (userChoice){
                case 1->author.showUI();
                case 2->editor.showUI();
                case 3->reviewer.showUI();
            }
        }while(userChoice!=4);



    }
}
