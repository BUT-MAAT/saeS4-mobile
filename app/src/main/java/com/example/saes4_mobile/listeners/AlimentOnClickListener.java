package com.example.saes4_mobile.listeners;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.saes4_mobile.R;
import com.example.saes4_mobile.data.SelectedAliments;

public class AlimentOnClickListener implements View.OnClickListener {

    private Activity activity;
    private int alimentId;

    public AlimentOnClickListener(Activity activity, int alimentId) {
        this.activity = activity;
        this.alimentId = alimentId;
    }

    @Override
    public void onClick(View v) {
        LinearLayout selectedLayout = activity.findViewById(R.id.selected_insert_layout);
        if (SelectedAliments.nbSelection() >= 10) {
            Toast.makeText(activity, "Il y a deja 10 aliments dans la liste", Toast.LENGTH_SHORT).show();
            return;
        }
        if (SelectedAliments.contains(alimentId)) {
            Toast.makeText(activity, "Aliment deja dans la liste", Toast.LENGTH_SHORT).show();
            return;
        }
        SelectedAliments.add(activity, alimentId);
        v.setOnClickListener(new SelectedAlimentOnClickListener(activity, alimentId));
        ((LinearLayout) v.getParent()).removeView(v);
        selectedLayout.addView(v);
    }
}
