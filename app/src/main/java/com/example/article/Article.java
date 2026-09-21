package com.example.article;

import java.io.Serializable;

// Class du lieu Article: title, content, img_cover (anh cover CUC BO, luu resource id,
// khong luu duong link), va so luot xem (view). Dung Serializable de gui
// nguyen object qua Intent, giong cach da duyet o lab truoc.
public class Article implements Serializable {

    private String title;
    private String content;
    private int img_cover; // id cua anh trong res/drawable, VD: R.drawable.article_cover_1
    private int view;

    public Article(String title, String content, int img_cover) {
        this.title = title;
        this.content = content;
        this.img_cover = img_cover;
        this.view = 0; // view ban dau luon la 0
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getImgCover() {
        return img_cover;
    }

    public int getView() {
        return view;
    }

    // Tang view len 1, goi khi nguoi dung bam vao bai viet de xem chi tiet
    public void increaseView() {
        view = view + 1;
    }
}