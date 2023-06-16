package com.example.saes4_mobile.pillfactories;

import android.app.Activity;
import android.widget.LinearLayout;

import com.example.saes4_mobile.R;
import com.example.saes4_mobile.listeners.AlimentOnClickListener;
import com.google.android.material.button.MaterialButton;

public class AlimentPillFactory extends PillFactory {
    public MaterialButton createPill(Activity activity, int alimentId, String alimentName) {
        MaterialButton pill = super.createBasePill(activity, alimentName, R.style.Aliment_Pill);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(5, 3, 5, 3);
        pill.setLayoutParams(params);

        pill.setOnClickListener(new AlimentOnClickListener(activity, alimentId));

        return pill;
    }
}
