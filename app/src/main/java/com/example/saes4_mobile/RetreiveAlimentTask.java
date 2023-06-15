package com.example.saes4_mobile;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.HttpClientBuilder;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONException;

class RetreiveAlimentTask extends AsyncTask<String, Void, JSONArray> {

    private Activity activity;
    private TextView debugZone;

    public RetreiveAlimentTask(Activity activity, TextView debugZone) {
        super();
        this.activity = activity;
        this.debugZone = debugZone;
    }

    protected JSONArray doInBackground(String...strings) {
        try {
            String requestURL = String.format(
                    activity.getResources().getString(R.string.api_url),
                    "/aliment/all"
            );

            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpGet httpget= new HttpGet(requestURL);

            HttpResponse response = httpclient.execute(httpget);

            String alimentsJSONString = EntityUtils.toString(response.getEntity());

            JSONArray aliments = new JSONArray(alimentsJSONString);
            Log.d(TAG, alimentsJSONString);
            return aliments;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void onPostExecute(JSONArray aliments) {
        try {
            if (aliments == null) debugZone.setText("Fetch result is null");
            else {
                debugZone.setText(aliments.getJSONObject(0).get("nom_aliment").toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

