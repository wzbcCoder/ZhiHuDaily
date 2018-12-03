package com.example.administrator.zhihunews.db.model;

/**
 * Created by Administrator on 2018/11/26.
 */
// 新闻条目实体类
public class NewsItem {
    private String gaPreix;
    private int id;//唯一id
    private String image;// 图片
    private String title;//标题
    private String type;//类型
    private String date ; //创建时间

    public NewsItem(String gaPreix, int id, String image, String title, String type, String date) {
        this.gaPreix = gaPreix;
        this.id = id;
        this.image = image;
        this.title = title;
        this.type = type;
        this.date = date;
    }

    public NewsItem() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
