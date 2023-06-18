package com.example.saes4_mobile;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saes4_mobile.listeners.SendArrowOnClicklistener;

import java.util.ArrayList;
import java.util.List;

public class SelectedAliments {

    private static List<Integer> alimentIds;

    static {
        alimentIds = new ArrayList<>();
    }

    public static void add(Activity activity, int newId) {
        if (alimentIds.contains(newId)) {
            return;
        }
        if(alimentIds.size() >= 10) {
            return;
        }
        alimentIds.add(newId);
        // Enable send
        TextView counter = activity.findViewById(R.id.send_counter);
        counter.setText(
                String.format(activity.getString(R.string.send_number_alim), alimentIds.size())
        );
        if (alimentIds.size() >= 10) {
            ImageView sendArrowButton = activity.findViewById(R.id.send_arrow);
            sendArrowButton.setVisibility(View.VISIBLE);
            counter.setVisibility(View.GONE);
        }
    }

    public static void remove(Activity activity, int id) {
        alimentIds.remove(alimentIds.indexOf(id));

        ImageView sendArrowButton = activity.findViewById(R.id.send_arrow);
        sendArrowButton.setVisibility(View.GONE);
        TextView counter = activity.findViewById(R.id.send_counter);
        counter.setVisibility(View.VISIBLE);
        counter.setText(
                String.format(activity.getString(R.string.send_number_alim), alimentIds.size())
        );
    }

    public static int nbSelection() {
        return alimentIds.size();
    }

    public static boolean contains(int alimentId) {
        return alimentIds.contains(alimentId);
    }

    public static List<Integer> getAlimentIds() {
        return alimentIds;
    }
}
