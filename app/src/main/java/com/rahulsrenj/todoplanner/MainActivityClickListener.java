package com.rahulsrenj.todoplanner;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class MainActivityClickListener {

    private Context context;
    public MainActivityClickListener(Context context){
        this.context=context;
    }
    public void onFABClicked(View view)
    {
        Intent i=new Intent(context, AddTasksActivity.class);
        context.startActivity(i);
    }
}
