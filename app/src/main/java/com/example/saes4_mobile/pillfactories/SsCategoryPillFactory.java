package com.example.saes4_mobile.pillfactories;

import android.app.Activity;
import android.content.Context;

import com.example.saes4_mobile.listeners.CategoryOnClickListener;
import com.example.saes4_mobile.listeners.SsCategoryOnClickListener;
import com.google.android.material.button.MaterialButton;

public class SsCategoryPillFactory extends PillFactory {

    @Override
    public MaterialButton createPill(Activity activity, int categoryId, String categoryName) {
        MaterialButton pill = super.createBasePill(activity, categoryName);

        pill.setOnClickListener(new SsCategoryOnClickListener(activity, categoryId, categoryName));

        return pill;
    }
}
