package com.example.watchnext.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE id = :userId")
    User getById(String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsert(User user);

    @Delete
    void delete(User user);
}
