package com.example.saes4_mobile.pillfactories;

import android.app.Activity;
import android.view.ContextThemeWrapper;

import com.example.saes4_mobile.R;
import com.google.android.material.button.MaterialButton;

public abstract class PillFactory {

    protected MaterialButton createBasePill(Activity activity, String title, int themeRef) {
        MaterialButton pill = new MaterialButton(new ContextThemeWrapper(activity, themeRef), null, 0);
        pill.setText(title);
        pill.setBackgroundDrawable(activity.getDrawable(R.drawable.pill_button_bg));

        return pill;
    }

    public abstract MaterialButton createPill(Activity activity, int categoryId, String categoryName);
}
