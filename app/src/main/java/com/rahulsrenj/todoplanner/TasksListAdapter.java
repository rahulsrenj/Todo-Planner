package com.rahulsrenj.todoplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.rahulsrenj.todoplanner.databinding.TasksListItemBinding;

import java.util.ArrayList;

public class TasksListAdapter extends RecyclerView.Adapter<TasksListAdapter.ViewHolder> {

    private ArrayList<Tasks> tasksArrayList;
    private TasksListItemBinding binding;
    private Context context;
    private TasksViewModel viewModel;

    public TasksListAdapter(Context context,ArrayList<Tasks> tasksArrayList,TasksViewModel viewModel){
        this.tasksArrayList=tasksArrayList;
        this.context=context;
        this.viewModel=viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        binding= DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.tasks_list_item,parent,false);


        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Tasks currentTasks=tasksArrayList.get(position);
        holder.binding.setTasks(currentTasks);

    }

    @Override
    public int getItemCount() {
        if(tasksArrayList!=null)
        {
            return tasksArrayList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TasksListItemBinding binding;

        public ViewHolder(TasksListItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
            binding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Tasks currentTasks=tasksArrayList.get(getAdapterPosition());
                    currentTasks.setCompleted(b);
                    viewModel.updateTasks(currentTasks);

                }
            });
        }
    }
}
