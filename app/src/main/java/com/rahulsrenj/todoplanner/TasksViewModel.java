package com.rahulsrenj.todoplanner;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TasksViewModel extends AndroidViewModel {
    private Repository repository;
    public TasksViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
    }
    public void addTasks(Tasks tasks)
    {
        repository.addTasks(tasks);
    }
    public void removeTasks(Tasks tasks)
    {
        repository.removeTasks(tasks);
    }
    public void updateTasks(Tasks tasks)
    {
        repository.updateTasks(tasks);
    }
    public LiveData<List<Tasks>> showAllTasks()
    {
        return repository.showAllTasks();
    }
}
