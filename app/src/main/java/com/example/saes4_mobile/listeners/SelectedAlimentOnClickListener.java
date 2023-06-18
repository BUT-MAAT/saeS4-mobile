package com.example.saes4_mobile.listeners;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.saes4_mobile.data.SelectedAliments;

public class SelectedAlimentOnClickListener implements View.OnClickListener {

    private Activity activity;
    private int alimentId;

    public SelectedAlimentOnClickListener(Activity activity, int alimentId) {
        this.activity = activity;
        this.alimentId = alimentId;
    }

    @Override
    public void onClick(View v) {
        SelectedAliments.remove(activity, alimentId);
        ((LinearLayout) v.getParent()).removeView(v);
    }
}
