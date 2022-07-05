package model;

public class ReviewModel {
    String id;
    String name;
    String comment;

    public ReviewModel() {
    }

    public ReviewModel(String id, String name, String comment) {
        this.id = id;
        this.name = name;
        this.comment = comment;
    }

    public ReviewModel(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getcomment() {
        return comment;
    }

    public void setcomment(String comment) {
        this.comment = comment;
    }
}
