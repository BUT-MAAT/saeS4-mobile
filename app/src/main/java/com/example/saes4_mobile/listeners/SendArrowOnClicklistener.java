package com.example.saes4_mobile.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.example.saes4_mobile.SendActivity;

public class SendArrowOnClicklistener implements View.OnClickListener {

    private Activity activity;
    public SendArrowOnClicklistener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(activity, SendActivity.class);
        activity.startActivity(intent);
    }
}
