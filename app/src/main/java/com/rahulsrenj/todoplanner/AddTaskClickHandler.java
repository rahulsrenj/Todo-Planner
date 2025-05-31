package com.rahulsrenj.todoplanner;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class AddTaskClickHandler {
    private Context context;
    private TasksViewModel viewModel;
    private Tasks tasks;

    public AddTaskClickHandler(Context context, TasksViewModel viewModel, Tasks tasks) {
        this.context = context;
        this.viewModel = viewModel;
        this.tasks = tasks;
    }

    public void onAddTaskClicked(View view)
    {
        if(tasks.getName()==null)
        {
            Toast.makeText(context, "Please add a task to continue.", Toast.LENGTH_SHORT).show();
        }else {
            Intent i = new Intent(context, MainActivity.class);
            Tasks t = new Tasks(tasks.getName(), false);
            viewModel.addTasks(t);
            context.startActivity(i);
        }
    }
}
