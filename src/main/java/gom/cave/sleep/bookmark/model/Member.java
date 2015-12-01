package gom.cave.sleep.bookmark.model;

/**
 * Created by sleepbear on 2015. 11. 30..
 */
public class Member {

    private long id;
    private String email;
    private String password;
    private String nickname;

    public Member() {
    }

    public Member(long id, String email, String password, String nickname) {

        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickName) {
        this.nickname = nickName;
    }
}
