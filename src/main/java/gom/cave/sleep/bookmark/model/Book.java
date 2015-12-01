package gom.cave.sleep.bookmark.model;

/**
 * Created by sleepbear on 2015. 11. 30..
 */
public class Book {

    private long id;
    private String title;

    private String writer;

    private String introduce;

    public Book() {
    }

    public Book(long id, String title, String writer, String publisher, String introduce) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.publisher = publisher;
        this.introduce = introduce;
    }

    public long getId() {
        return id;
    }

    private String publisher;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
