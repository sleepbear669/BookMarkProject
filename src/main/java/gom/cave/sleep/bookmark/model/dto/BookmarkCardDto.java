package gom.cave.sleep.bookmark.model.dto;

import gom.cave.sleep.bookmark.model.Comment;

import java.util.List;

/**
 * Created by sleepbear on 2015. 12. 3..
 */
public class BookmarkCardDto {

    private long id;
    private String nickname;
    private String title;
    private String phrase;
    private String tag;
    private List<Comment> commentList;
    public BookmarkCardDto(long id, String nickname, String title, String phrase, String tag) {

        this.id = id;
        this.nickname = nickname;
        this.title = title;
        this.phrase = phrase;
        this.tag = tag;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
