package com.example.marvelheroes.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HeroDao {
    @Insert
    void insert(Hero hero);

    @Update
    void update(Hero hero);

    @Delete
    void delete(Hero hero);

    @Query("DELETE FROM heroes_table")
    void deleteAll();

    @Query("SELECT * FROM heroes_table")
    LiveData<List<Hero>> getAllHeroes();
}
