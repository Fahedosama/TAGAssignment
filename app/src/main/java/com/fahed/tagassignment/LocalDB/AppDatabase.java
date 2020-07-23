package com.fahed.tagassignment.LocalDB;

import android.content.ClipData;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.fahed.tagassignment.Data.Model.News;
import com.fahed.tagassignment.LocalDB.DAO.NewsDao;


@Database(entities = {News.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "news_db";

    private static AppDatabase instance;

    public static AppDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    DATABASE_NAME
            ).allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract NewsDao getItemDAO();
}