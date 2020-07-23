package com.fahed.tagassignment.Screen2;

import com.fahed.tagassignment.Data.Model.News;

public class NewsDetailsPresenter {

    private NewsDetailsActivity newsDetailsActivity;
    private News currentItem;

    public NewsDetailsPresenter(NewsDetailsActivity newsDetailsActivity, News currentItem) {
        this.newsDetailsActivity = newsDetailsActivity;
        this.currentItem = currentItem;
    }

    public News getCurrentItem(){
        return currentItem;
    }
}
