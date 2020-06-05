package com.example.badumdes.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TestimoniDAO {
    @Insert
    long insertTestimoni(TestimoniModel testimoniModel);

    @Delete
    int deleteTestimoni(TestimoniModel testimoniModel);

    @Query("SELECT * FROM data_testimoni")
    List<TestimoniModel> getTestimoni();
}
