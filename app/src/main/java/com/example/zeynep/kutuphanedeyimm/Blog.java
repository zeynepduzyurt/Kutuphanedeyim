package com.example.zeynep.kutuphanedeyimm;
// Kitap Önerisi için açılan Blog sayfası
/**
 * Created by Zeynep on 3.05.2018.
 */

public class Blog {
    private String title;
    private String desc;
    private String image;

    public Blog() {
    }

    public Blog(String title, String desc, String image) {

        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
