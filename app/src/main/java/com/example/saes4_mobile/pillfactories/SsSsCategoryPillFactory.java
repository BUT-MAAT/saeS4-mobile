package com.example.saes4_mobile.pillfactories;

import android.app.Activity;

import com.example.saes4_mobile.R;
import com.example.saes4_mobile.listeners.SsCategoryOnClickListener;
import com.example.saes4_mobile.listeners.SsSsCategoryOnClickListener;
import com.google.android.material.button.MaterialButton;

public class SsSsCategoryPillFactory extends PillFactory {

    @Override
    public MaterialButton createPill(Activity activity, int categoryId, String categoryName) {
        MaterialButton pill = super.createBasePill(activity, categoryName, R.style.CollapseLayout_Child_Pill);

        pill.setOnClickListener(new SsSsCategoryOnClickListener(activity, categoryId, categoryName));

        return pill;
    }
}
