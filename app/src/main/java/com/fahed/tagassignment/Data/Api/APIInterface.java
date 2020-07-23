package com.fahed.tagassignment.Data.Api;

import com.fahed.tagassignment.Data.Model.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("media_news?strGroup=news&lang=en&strStatus=1")
    Call<List<News>> listNews();

}
