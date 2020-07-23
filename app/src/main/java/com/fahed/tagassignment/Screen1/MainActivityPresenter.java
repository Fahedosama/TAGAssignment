package com.fahed.tagassignment.Screen1;

import android.util.Log;
import com.fahed.tagassignment.Data.APIClient;
import com.fahed.tagassignment.Data.Api.APIInterface;
import com.fahed.tagassignment.Data.Model.News;
import com.fahed.tagassignment.LocalDB.AppDatabase;
import com.fahed.tagassignment.LocalDB.DAO.NewsDao;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityPresenter {

    private MainActivity mainActivity;
    private APIInterface apiInterface;
    private NewsDao newsDAO;

    public MainActivityPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        apiInterface = APIClient.getRetrofitInstance().create(APIInterface.class);

        AppDatabase database = AppDatabase.getInstance(mainActivity);

        newsDAO = database.getItemDAO();

        callApi();

    }


    private void callApi() {
        Call<List<News>> call = apiInterface.listNews();
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {

                if (response.isSuccessful()) {
                    Log.d("TAG", response.code() + "");

                    addDatatoRoom(response.body());

                } else {
                    System.out.println(response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                //on failure print the error
                t.printStackTrace();
            }

        });

    }

    //get all news items from database
    public List<News> getListNews() {
        return newsDAO.getItems();
    }

    //delete all news from table
    public void deleteAllNews() {
        newsDAO.deleteAllData();
    }

    //get number of rows in database
    public int getCount() {
        return newsDAO.getCount();
    }

    //show how many news exists
    public void showCount() {
        mainActivity.showMessage("Number of news is " + getCount());
    }

    //add item as test
    public void addItem() {
        News q = new News("Fahed" + newsDAO.getLastId(), "2020-07-23T00:00:00");
        newsDAO.insert(q);
        //show success message
        mainActivity.showMessage("Item Added Successfully!");
    }


    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void addDatatoRoom(List<News> dataList) {
        //clear all data
        newsDAO.deleteAllData();

        //add all data to room
        for (News n : dataList) {
            newsDAO.insert(n);
        }
    }
}


