package models;

public class User {

    private String name;
    private String nickName;
    private String password;

    public User(String nickName, String password,String name) {
        this.password = password;
        this.nickName = nickName;
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
