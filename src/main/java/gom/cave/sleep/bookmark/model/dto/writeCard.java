package gom.cave.sleep.bookmark.model.dto;

/**
 * Created by sleepbear on 2015. 12. 3..
 */
public class WriteCard {
    private String phrase;
    private String tag;
    private String title;
    private long memberId;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }
}
