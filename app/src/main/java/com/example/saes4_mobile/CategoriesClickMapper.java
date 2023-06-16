package com.example.saes4_mobile;

import android.app.Activity;

import com.google.android.material.button.MaterialButton;

import java.util.HashMap;

@Deprecated
public class CategoriesClickMapper {
    private static Activity activity;
    private static HashMap<String, MaterialButton> map;

    public CategoriesClickMapper(Activity activity) {
        if(CategoriesClickMapper.activity == null) {
            CategoriesClickMapper.activity = activity;
        }

        if (map == null) {
            map = new HashMap<>();
            map.put("category", null);
            map.put("sscategory", null);
            map.put("sssscategory", null);
        }
    }
}
