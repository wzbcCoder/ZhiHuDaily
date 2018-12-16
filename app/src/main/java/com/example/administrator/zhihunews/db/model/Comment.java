package com.example.administrator.zhihunews.db.model;

/**
 * Created by Administrator on 2018/12/14.
 */

public class Comment {
    private String author;
    private String avatar;
    private String content;
    private int time;
    private int likes;

    public Comment(String author, String avatar, String content, int time,int likes) {
        this.author = author;
        this.avatar = avatar;
        this.content = content;
        this.time = time;
        this.likes=likes;
    }
    public Comment(){
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }
}
