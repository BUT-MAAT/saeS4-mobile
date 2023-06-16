package com.example.saes4_mobile.listeners;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import com.example.saes4_mobile.R;
import com.example.saes4_mobile.fetchtasks.RetreiveCategoriesTask;
import com.example.saes4_mobile.pillfactories.SsSsCategoryPillFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SsCategoryOnClickListener implements View.OnClickListener {

    private Activity activity;
    private int categoryId;
    private String categoryName;

    public SsCategoryOnClickListener(Activity activity, int categoryId, String categoryName) {
        this.activity = activity;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    @Override
    public void onClick(View v) {
        TextView categoryTitle = activity.findViewById(R.id.sscategorie_collapse_title);
        categoryTitle.setText(
                String.format(activity.getString(R.string.catergorie_collapse_title), categoryName)
        );
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(
            new RetreiveCategoriesTask(activity,
                    String.format(activity.getString(R.string.api_categories_byId_path), categoryId),
                    activity.findViewById(R.id.sssscategorie_insert_layout),
                    new SsSsCategoryPillFactory(),
                    new Handler(Looper.getMainLooper())
            )
        );
    }
}