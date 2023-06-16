package com.example.saes4_mobile.listeners;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.example.saes4_mobile.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SsSsCategoryOnClickListener implements View.OnClickListener {

    private Activity activity;
    private int categoryId;
    private String categoryName;

    public SsSsCategoryOnClickListener(Activity activity, int categoryId, String categoryName) {
        this.activity = activity;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    @Override
    public void onClick(View v) {
        TextView categoryTitle = activity.findViewById(R.id.sssscategorie_collapse_title);
        categoryTitle.setText(
                String.format(activity.getString(R.string.catergorie_collapse_title), categoryName)
        );
        ExecutorService executor = Executors.newSingleThreadExecutor();
//        executor.execute();
    }
}
