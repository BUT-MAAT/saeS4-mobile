package com.example.saes4_mobile.listeners;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.util.Log;
import android.view.View;

public class CollapseLayoutListener implements View.OnClickListener {

    private View mainLayout;
    private View childLayout;
    private View arrow;

    private int ROTATION;

    private boolean collapsed = true;

    public CollapseLayoutListener(View mainLayout, View childLayout, View arrow) {
        this.mainLayout = mainLayout;
        this.childLayout = childLayout;
        this.arrow = arrow;
    }

    @Override
    public void onClick(View v) {
        int height = v.getHeight();

        if (collapsed) {
            mainLayout.getLayoutParams().height = height * 2;
            mainLayout.requestLayout();
            childLayout.animate()
                    .translationY(height)
                    .setDuration(300)
                    .start();
            ROTATION = 0;
        }
        else {
            mainLayout.getLayoutParams().height = height;
            mainLayout.requestLayout();
            childLayout.animate()
                    .translationY(-height)
                    .setDuration(300)
                    .start();
            ROTATION = 90;
        }
        arrow.animate()
                .rotation(ROTATION)
                .setDuration(300)
                .start();

        collapsed = !collapsed;
    }
}
