package com.rahulsrenj.todoplanner;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rahulsrenj.todoplanner.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<Tasks> tasksArrayList=new ArrayList<>();
    private TasksViewModel viewModel;
    private TasksDatabase tasksDatabase;
    private RecyclerView recyclerView;
    private TasksListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.setClickHandler(new MainActivityClickListener(this));
        viewModel=new ViewModelProvider(this).get(TasksViewModel.class);
        recyclerView=binding.recyclerView;
        tasksDatabase=TasksDatabase.getTaskDatabase(this);
        adapter=new TasksListAdapter(this,tasksArrayList,viewModel);
        viewModel.showAllTasks().observe(this, new Observer<List<Tasks>>() {
            @Override
            public void onChanged(List<Tasks> tasks) {
                tasksArrayList.clear();
                tasksArrayList.addAll(tasks);
                adapter.notifyDataSetChanged();
                if (tasks.isEmpty())
                {
                    binding.noTaskItem.setVisibility(View.VISIBLE);
                    binding.recyclerView.setVisibility(View.GONE);

                }
                else{
                    binding.noTaskItem.setVisibility(View.GONE);
                    binding.recyclerView.setVisibility(View.VISIBLE);
                }
            }

        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Tasks currentTask=tasksArrayList.get(viewHolder.getAdapterPosition());
                viewModel.removeTasks(currentTask);
            }
        }).attachToRecyclerView(recyclerView);
    }
}