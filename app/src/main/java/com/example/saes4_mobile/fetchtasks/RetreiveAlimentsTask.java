package com.example.saes4_mobile.fetchtasks;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.saes4_mobile.R;
import com.example.saes4_mobile.pillfactories.PillFactory;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ClientProtocolException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ResponseHandler;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.HttpClientBuilder;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RetreiveAlimentsTask implements Runnable {

    private Activity activity;
    private String path;
    private LinearLayout alimentLinearLayout;
    private PillFactory pillFactory;
    private Handler handler;


    public RetreiveAlimentsTask(Activity activity, String path,
                                LinearLayout alimentLinearLayout, PillFactory pillFactory,
                                Handler handler) {
        super();
        this.activity = activity;
        this.path = path;
        this.alimentLinearLayout = alimentLinearLayout;
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

            activity.findViewById(R.id.aliment_message).setVisibility(View.GONE);
            activity.findViewById(R.id.aliment_loader).setVisibility(View.VISIBLE);

            HttpResponse response = httpclient.execute(httpget, new ResponseHandler<HttpResponse>() {
                @Override
                public HttpResponse handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                    activity.findViewById(R.id.aliment_loader).setVisibility(View.GONE);
                    if(httpResponse == null) {
                        activity.findViewById(R.id.aliment_message).setVisibility(View.GONE);
                    }
                    return httpResponse;
                }
            });

            String alimentsJSONString = EntityUtils.toString(response.getEntity());

            JSONArray aliments = new JSONArray(alimentsJSONString);

            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (aliments == null) Toast.makeText(activity,"Fetch result is null", Toast.LENGTH_SHORT);
                        else {
                            alimentLinearLayout.removeAllViews();
                            for (int i = 0; i < aliments.length(); i++) {
                                JSONObject aliment = aliments.getJSONObject(i);
                                alimentLinearLayout.addView(
                                        pillFactory.createPill(activity,
                                                (Integer) aliment.get("id_aliment"),
                                                (String) aliment.get("nom_aliment")
                                        )
                                );
                                alimentLinearLayout.addView(new View(new ContextThemeWrapper(activity, R.style.Aliment_PillSpacer), null, 0));
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

