package com.rahulsrenj.todoplanner;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.rahulsrenj.todoplanner.databinding.ActivityAddTasksBinding;

public class AddTasksActivity extends AppCompatActivity {

    private ActivityAddTasksBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_tasks);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Tasks tasks=new Tasks();
        TasksViewModel viewModel=new ViewModelProvider(this).get(TasksViewModel.class);
        binding.setTasks(tasks);
        binding.setClickHandler(new AddTaskClickHandler(this,viewModel,tasks));
    }
}