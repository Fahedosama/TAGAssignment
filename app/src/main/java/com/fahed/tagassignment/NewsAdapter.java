package com.fahed.tagassignment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.fahed.tagassignment.Data.Model.News;

import java.text.ParseException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<News> newsList;
    Context mContext;

    public NewsAdapter(List<News> newsList, Context mContext) {
        this.newsList = newsList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShowViewHolder(LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final ShowViewHolder viewHolder = (ShowViewHolder) holder;
        final News currentNews = newsList.get(position);

        viewHolder.titleTextView.setText(currentNews.getTitle());
        try {
            viewHolder.dateTextView.setText(currentNews.getFormat());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        viewHolder.progressBar.setVisibility(View.VISIBLE);
        Glide.with(mContext).load(currentNews.getImage()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                viewHolder.progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class ShowViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cardview)
        CardView cardView;
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.txtTitle)
        TextView titleTextView;
        @BindView(R.id.txtDate)
        TextView dateTextView;
        @BindView(R.id.progressBar3)
        ProgressBar progressBar;
        public ShowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
