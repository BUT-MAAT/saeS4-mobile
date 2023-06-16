package com.example.saes4_mobile.fetchtasks;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.saes4_mobile.R;
import com.example.saes4_mobile.pillfactories.CategoryPillFactory;
import com.example.saes4_mobile.pillfactories.PillFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.HttpClientBuilder;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RetreiveCategoriesTask implements Runnable {

    private Activity activity;
    private String path;
    private LinearLayout categoryLinearLayout;
    private PillFactory pillFactory;
    private Handler handler;


    public RetreiveCategoriesTask(Activity activity, String path,
                                  LinearLayout categoryLinearLayout, PillFactory pillFactory,
                                  Handler handler) {
        super();
        this.activity = activity;
        this.path = path;
        this.categoryLinearLayout = categoryLinearLayout;
        this.handler = handler;
        this.pillFactory = pillFactory;
    }

    @Override
    public void run() {

        try {
            String requestURL = String.format(
                    activity.getResources().getString(R.string.api_url),
                    this.path
            );

            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpGet httpget= new HttpGet(requestURL);

            HttpResponse response = httpclient.execute(httpget);

            String categoriesJSONString = EntityUtils.toString(response.getEntity());

            JSONArray categories = new JSONArray(categoriesJSONString);
            Log.d(TAG, categoriesJSONString);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (categories == null) Toast.makeText(activity,"Fetch result is null", Toast.LENGTH_SHORT);
                        else {
                            categoryLinearLayout.removeAllViews();
                            for(int i = 0; i < categories.length(); i++) {
                                JSONObject aliment = categories.getJSONObject(i);
                                categoryLinearLayout.addView(
                                        pillFactory.createPill(activity,
                                                (Integer) aliment.get("id_categorie"),
                                                (String) aliment.get("nom_categorie")
                                        )
                                );
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

