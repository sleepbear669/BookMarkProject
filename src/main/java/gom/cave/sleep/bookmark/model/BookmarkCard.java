package gom.cave.sleep.bookmark.model;

/**
 * Created by sleepbear on 2015. 11. 30..
 */
public class BookmarkCard {

    private long id;
    private long memberId;
    private Long bookId;
    private String phrase;
    private String tag;

    public BookmarkCard() {
    }

    public BookmarkCard(long id, long memberId, Long bookId, String pharse, String tag) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.phrase = pharse;
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

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
