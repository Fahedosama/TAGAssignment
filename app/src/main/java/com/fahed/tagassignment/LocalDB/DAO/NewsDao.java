package com.fahed.tagassignment.LocalDB.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.fahed.tagassignment.Data.Model.News;


import java.util.List;

@Dao
public interface NewsDao {

    @Insert
    public void insert(News... items);
    @Update
    public void update(News... items);
    @Delete
    public void delete(News item);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertList(List<News> items);
    
    @Query("SELECT * FROM news")
    public List<News> getItems();

    @Query("SELECT * FROM news WHERE id = :id")
    public News getItemById(int id);

    @Query("SELECT max(id) FROM news")
    public int getLastId();

    @Query("SELECT count(*) FROM news")
    public int getCount();

    @Query("DELETE FROM news")
    public void deleteAllData();
}
