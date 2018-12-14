package com.example.administrator.zhihunews.db.model;

/**
 * Created by Administrator on 2018/12/14.
 */

public class Comment {
    private String author;
    private String avatar;
    private String context;
    private Long time;
    private int likes;

    public Comment(String author, String avatar, String context, Long time,int likes) {
        this.author = author;
        this.avatar = avatar;
        this.context = context;
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }
}
