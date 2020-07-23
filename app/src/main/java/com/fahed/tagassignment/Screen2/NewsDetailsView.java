package com.fahed.tagassignment.Screen2;

import com.fahed.tagassignment.Data.Model.News;

import java.text.ParseException;

public interface NewsDetailsView {

    void showData(News currentNews) throws ParseException;
}
