package com.example.saes4_mobile.pillfactories;

import android.app.Activity;

import com.example.saes4_mobile.R;
import com.example.saes4_mobile.listeners.CategoryOnClickListener;
import com.google.android.material.button.MaterialButton;

public class AlimentPillFactory extends PillFactory {

    @Override
    public MaterialButton createPill(Activity activity, int categoryId, String categoryName) {
        MaterialButton pill = super.createBasePill(activity, categoryName, R.style.Aliment_Pill);

//        pill.setOnClickListener(new CategoryOnClickListener(activity, categoryId, categoryName));

        return pill;
    }
}
