package com.example.administrator.zhihunews.db.model;

/**
 * Created by Administrator on 2018/11/26.
 */

public class NewsItem {
    private String gaPreix;
    private int id;//唯一id
    private String image;// 图片
    private String title = "title";//标题
    private String type = "type";//类型


    public NewsItem(String gaPreix, int id, String image, String title, String type) {
        this.gaPreix = gaPreix;
        this.id = id;
        this.image = image;
        this.title = title;
        this.type = type;
    }

    public String getGaPreix() {
        return gaPreix;
    }

    public void setGaPreix(String gaPreix) {
        this.gaPreix = gaPreix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
