package com.example.shoaibiqbal.takecare;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by Shoaib Iqbal on 06/04/2016.
 */
public class ArticleCard {

    private String title;
    private int imageResourceID;

    public ArticleCard(String Title) {
        title = Title;
        imageResourceID = R.drawable.ic_launcher;
    }
    public ArticleCard(String Title, int pictureID) {
        title = Title;
        imageResourceID = pictureID;
    }

    public String getTitle() { return title; }
    public int getImage() { return imageResourceID; }
}
