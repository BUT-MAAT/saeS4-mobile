package com.example.saes4_mobile.pillfactories;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.saes4_mobile.R;
import com.google.android.material.button.MaterialButton;

public class AlimentPillFactory extends PillFactory {

    @Override
    public MaterialButton createPill(Activity activity, int categoryId, String categoryName) {
        MaterialButton pill = super.createBasePill(activity, categoryName, R.style.Aliment_Pill);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(5, 3, 5, 3);
        pill.setLayoutParams(params);

//        pill.setOnClickListener(new CategoryOnClickListener(activity, categoryId, categoryName));

        return pill;
    }
}
