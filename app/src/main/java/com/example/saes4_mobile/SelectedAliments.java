package com.example.saes4_mobile;

import android.app.Activity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SelectedAliments {

    private static List<Integer> alimentIds;

    static {
        alimentIds = new ArrayList<>();
    }

    public static void add(int newId) {
        if (alimentIds.contains(newId)) {
            return;
        }
        if(alimentIds.size() >= 10) {
            return;
        }
        alimentIds.add(newId);
    }

    public static void remove(int id) {
        alimentIds.remove(alimentIds.indexOf(id));
    }

    public static int nbSelection() {
        return alimentIds.size();
    }

    public static boolean contains(int alimentId) {
        return alimentIds.contains(alimentId);
    }
}
