package com.rahulsrenj.todoplanner;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Tasks.class},version = 1)
public abstract class TasksDatabase extends RoomDatabase {

    private static TasksDatabase dbInstance;
    public abstract TasksDAO getTasksDAO();

    public static synchronized TasksDatabase getTaskDatabase(Context context)
    {
        if(dbInstance==null)
        {
            dbInstance= Room.databaseBuilder(context.getApplicationContext(), TasksDatabase.class,"tasks_db").build();

        }
        return dbInstance;
    }

}
