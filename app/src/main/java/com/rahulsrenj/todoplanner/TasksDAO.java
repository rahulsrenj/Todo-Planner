package com.rahulsrenj.todoplanner;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TasksDAO {

    @Insert
    void addTasks(Tasks tasks);
    @Delete
    void removeTasks(Tasks tasks);
    @Update
    void updateTasks(Tasks tasks);
    @Query("SELECT * FROM tasks_table")
    LiveData<List<Tasks>> showAllTasks();
}
