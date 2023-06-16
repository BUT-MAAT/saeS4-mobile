package com.example.saes4_mobile.pillfactories;

import android.app.Activity;

import com.example.saes4_mobile.listeners.CategoryOnClickListener;
import com.google.android.material.button.MaterialButton;

public class CategoryPillFactory extends PillFactory {

    @Override
    public MaterialButton createPill(Activity activity, int categoryId, String categoryName) {
        MaterialButton pill = super.createBasePill(activity, categoryName);

        pill.setOnClickListener(new CategoryOnClickListener(activity, categoryId, categoryName));

        return pill;
    }
}
