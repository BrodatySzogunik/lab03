package baseq;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Article {
    private
    private String title;
    private String body;
    private String author;
    private boolean acceptedByEditor;
    private boolean acceptedByReviewer;

    public Article(String title,String body,String author,boolean acceptedByEditor, boolean acceptedByReviewer){
        this.title=title;
        this.body=body;
        this.author=author;
        this.acceptedByEditor=acceptedByEditor;
        this.acceptedByReviewer=acceptedByReviewer;
    }

    public void acceptByEditor(){
        this.acceptedByEditor = true;
    }

    public void acceptByReviewer(){
        this.acceptedByReviewer = true;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAcceptedByEditor() {
        return acceptedByEditor;
    }

    public boolean isAcceptedByReviewer() {
        return acceptedByReviewer;
    }

    @Override

    public String toString(){
        return this.title+", "+this.body+", "+this.author;
    }

}
