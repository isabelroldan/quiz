package org.example.model;

public class Player {
    private int id;
    private String nickname;
    private int age;

    public Player(int id, String nickname, int age) {
        this.id = id;
        this.nickname = nickname;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Player{" + "id=" + id + ", nickname='" + nickname + '\'' + ", age=" + age + '}';
    }
}
