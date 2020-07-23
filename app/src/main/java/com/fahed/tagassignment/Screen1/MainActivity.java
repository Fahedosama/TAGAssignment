package com.fahed.tagassignment.Screen1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fahed.tagassignment.Data.Model.News;
import com.fahed.tagassignment.NewsAdapter;
import com.fahed.tagassignment.Screen2.NewsDetailsActivity;
import com.fahed.tagassignment.R;
import com.fahed.tagassignment.RecyclerItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;



    private MainActivityPresenter mainPresenter;

    private List<News> listNews;

    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //assign presenter
        mainPresenter = new MainActivityPresenter(this);

        //get data from local db
        listNews = mainPresenter.getListNews();

        //initialize RecyclerView
        initRecyclerView();



    }


    @Override
    public void initRecyclerView() {
        //make newsAdapter for recyclerview
        newsAdapter = new NewsAdapter(listNews, this);

        recyclerView.setAdapter(newsAdapter);

        //set recyclerView layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //handle click to show each news alone
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(MainActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //make new intent
                        Intent intent = new Intent(MainActivity.this, NewsDetailsActivity.class);
                        //put news item as an extra to send it to the other activity
                        intent.putExtra("myCurrentNews", newsAdapter.newsList.get(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever

                    }
                })
        );
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
