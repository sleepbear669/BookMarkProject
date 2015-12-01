package gom.cave.sleep.bookmark.model;

/**
 * Created by sleepbear on 2015. 11. 30..
 */
public class Comment {

    private long id;
    private long bookMarkCardId;
    private String Comment;

    public Comment(long id, long bookMarkCardId, String comment) {
        this.id = id;
        this.bookMarkCardId = bookMarkCardId;
        Comment = comment;
    }

    public long getId() {
        return id;
    }

    public long getBookMarkCardId() {
        return bookMarkCardId;
    }

    public void setBookMarkCardId(long bookMarkCardId) {
        this.bookMarkCardId = bookMarkCardId;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
