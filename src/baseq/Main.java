package baseq;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArticleBase.readFromFile();

        System.out.println(ArticleBase.getArticleBase());

    }
}
