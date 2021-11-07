package baseq;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Article {
    private String title;
    private String body;
    private String author;
    private String review;
    private boolean acceptedByEditor;
    private boolean acceptedByReviewer;
    private boolean edited;
    private boolean sentToEditor;
    private boolean sentToReviewer;
    private boolean rejected;

    public Article(
            String title
            ,String body
            ,String author
            ,String review
            ,boolean acceptedByEditor
            ,boolean acceptedByReviewer
            ,boolean edited
            ,boolean sentToEditor
            ,boolean sentToReviewer
            ,boolean rejected){
        this.title=title;
        this.body=body;
        this.author=author;
        this.review=review;
        this.acceptedByEditor=acceptedByEditor;
        this.acceptedByReviewer=acceptedByReviewer;
        this.edited = edited;
        this.sentToEditor = sentToEditor;
        this.sentToReviewer = sentToReviewer;
        this.rejected = rejected;
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

    public boolean isEdited() {
        return edited;
    }

    public boolean isSentToEditor() {
        return sentToEditor;
    }

    public boolean isAcceptedByEditor() {
        return acceptedByEditor;
    }

    public boolean isAcceptedByReviewer() {
        return acceptedByReviewer;
    }

    public boolean isSentToReviewer() {
        return sentToReviewer;
    }

    public boolean isRejected() {
        return rejected;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAcceptedByEditor(boolean acceptedByEditor) {
        this.acceptedByEditor = acceptedByEditor;
    }

    public void setAcceptedByReviewer(boolean acceptedByReviewer) {
        this.acceptedByReviewer = acceptedByReviewer;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    public void setSentToEditor(boolean sentToEditor) {
        this.sentToEditor = sentToEditor;
    }

    public void setSentToReviewer(boolean sentToReviewer) {
        this.sentToReviewer = sentToReviewer;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override

    public String toString(){
        String status;
        if(sentToEditor){
            status = "zgłoszono do edytora";
        }else{
            status = "Czeka na zgłoszenie do etytora";
        }
        if(acceptedByEditor){
            status = "czeka na akceptację przez Recenzenta";
        }
        if(acceptedByReviewer){
            status = "Opublikowany";
        }
        if(edited){
            status+=", edytowano";
        }

        return this.title+", "+this.body+", "+this.author+", status: "+status+", "+(sentToReviewer?(review.length()<1?"recenzja: brak":"recenzja:"+review):"");
    }

}
