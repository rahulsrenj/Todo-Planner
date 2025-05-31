package com.rahulsrenj.todoplanner;

import android.app.Application;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

public class Repository {

    private TasksDAO tasksDAO;
    private ExecutorService executor;
    private Handler handler;
    public Repository(Application application)
    {
        TasksDatabase tasksDatabase=TasksDatabase.getTaskDatabase(application);
        tasksDAO= tasksDatabase.getTasksDAO();
        executor= Executors.newSingleThreadExecutor();
        handler=new Handler(Looper.getMainLooper());
    }
    public void addTasks(Tasks tasks)
    {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                tasksDAO.addTasks(tasks);
            }
        });
    }
    public void removeTasks(Tasks tasks)
    {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                tasksDAO.removeTasks(tasks);
            }
        });
    }
    public void updateTasks(Tasks tasks)
    {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                tasksDAO.updateTasks(tasks);
            }
        });
    }

    public LiveData<List<Tasks>> showAllTasks(){
        return tasksDAO.showAllTasks();
    }

}
