package baseq;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArticleBase.readFromFile();
        ArticleBase.addArticle(new Article("sd"
                ,"sdf"
                ,"sdf"
                ,""
                ,true
                ,true
                ,false
                ,true
                ,false
                ,false));

        AuthorUI author= new AuthorUI();
        EditorUI editor = new EditorUI();
        ReviewerUI reviewerUI = new ReviewerUI();

        reviewerUI.showUI();

    }
}
