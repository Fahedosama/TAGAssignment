package com.fahed.tagassignment.Screen2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fahed.tagassignment.Data.Model.News;
import com.fahed.tagassignment.R;
import com.github.chrisbanes.photoview.PhotoView;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailsActivity extends AppCompatActivity implements NewsDetailsView {

    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtDate)
    TextView txtDate;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.newsImage)
    PhotoView newsImage;

    NewsDetailsPresenter newsDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        News currentNews = getIntent().getExtras().getParcelable("myCurrentNews");

        newsDetailsPresenter = new NewsDetailsPresenter(this,currentNews);

        try {
            showData(newsDetailsPresenter.getCurrentItem());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public void showData(News currentNews) throws ParseException {
        webView.setBackgroundColor(Color.TRANSPARENT);

        txtTitle.setText(currentNews.getTitle());
        txtDate.setText(currentNews.getFormat());
        Glide.with(this).load(currentNews.getImage()).into(newsImage);
        String a = currentNews.getDescription().replaceAll("\r\n", "").replaceAll("http:", "https:");
        webView.loadData(a, "text/html", "UTF-8");
    }
}
