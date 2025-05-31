package com.rahulsrenj.todoplanner;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks_table")
public class Tasks {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tasks_id")
    private int id;
    @ColumnInfo(name = "tasks_name")
    private String name;
    @ColumnInfo(name = "tasks_completed")
    private boolean isCompleted;

    public Tasks(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public Tasks() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
