package gom.cave.sleep.bookmark.model;

/**
 * Created by sleepbear on 2015. 11. 30..
 */
public class BookmarkCard {

    private long id;
    private long memberId;
    private long bookId;
    private long bookMark;
    private String tag;

    public BookmarkCard() {
    }

    public BookmarkCard(long id, long memberId, long bookId, long bookMark, String tag) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.bookMark = bookMark;
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getBookMark() {
        return bookMark;
    }

    public void setBookMark(long bookMark) {
        this.bookMark = bookMark;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
