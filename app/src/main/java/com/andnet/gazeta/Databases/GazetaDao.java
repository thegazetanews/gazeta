package com.andnet.gazeta.Databases;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.andnet.gazeta.Models.Body;
import com.andnet.gazeta.Models.Category;
import com.andnet.gazeta.Models.Keys;
import com.andnet.gazeta.Models.News;
import com.andnet.gazeta.Models.Source;

import java.util.List;

@Dao
public interface GazetaDao{

    @Query("SELECT * FROM news")
    List<News> getAllSavedNews();

    @Query("SELECT * FROM body")
    List<Body> getAllBody();

    @Query("SELECT * FROM keys")
    List<Keys> getAllKeys();

    @Query("SELECT * FROM source")
    List<Source> getAllSource();

    @Query("SELECT * FROM Category")
    List<Category> getAllCatagory();

    @Query("SELECT * FROM news WHERE keys  = :newsId")
    List<News> getNews(String newsId);

    @Query("SELECT * FROM body WHERE keys = :bodyId")
    List<Body> getBodyId(String bodyId);

    @Query("SELECT * FROM news WHERE keys = :newsId")
    List<News> isNewsSaved(String newsId);

    @Query("SELECT * FROM keys WHERE keys = :val")
    List<Keys> isKeyExist(String val);

    @Query("SELECT `rowid` FROM source WHERE link  = :url")
    Long isSoureExist(String url);

    @Query("SELECT `rowid` FROM category WHERE name  = :name")
    Long isCatExist(String name);

    @Query("SELECT * FROM source WHERE visibility = 1")
    List<Source> getAllenabledSource();


    @Query("SELECT * FROM category WHERE visibility = 1")
    List<Category> getAllVisibleCategories();


    @Query("SELECT * FROM source WHERE name = :name")
    List<Source> getSource(String name);


    @Update
    void updateSource(Source source);

    @Update
    void updateCategory(Category category);

    @Insert
    void insertSource(Source...sources);

    @Insert
    void insertNews(News...news);

    @Insert
    void insertBody(Body...bodies);

    @Insert
    void insertKey(Keys...keys);

    @Insert
    void insertCategory(Category category);


    @Delete
    void deleteNews(News news);

    @Delete
    void deleteBody(Body news);

    @Delete
    void clearNews(List<News> newsList);

    @Delete
    void clearBody(List<Body> bodyList);

    @Delete
    void clearKeys(List<Keys> keysList);












}
