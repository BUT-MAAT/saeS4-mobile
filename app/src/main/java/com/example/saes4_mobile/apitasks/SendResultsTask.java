package com.example.saes4_mobile.apitasks;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.example.saes4_mobile.R;
import com.example.saes4_mobile.SuccessActivity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.CloseableHttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.entity.StringEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.CloseableHttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.HttpClients;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.protocol.HttpContext;

import org.json.JSONObject;

import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class SendResultsTask implements Runnable {

    private Activity activity;
    private String path;
    private Map<String, Object> data;
    private Handler handler;


    public SendResultsTask(Activity activity, String path, Map<String, Object> data, Handler handler) {
        super();
        this.activity = activity;
        this.path = path;
        this.handler = handler;
        this.data = data;
    }

    @Override
    public void run() {
        try {
            final HttpPost httpPost = new HttpPost(String.format(
                    activity.getResources().getString(R.string.api_url),
                    path)
            );

            final StringEntity entity = new StringEntity(stringifyMap(data));
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");

            CloseableHttpClient client = HttpClients.createDefault();
            CloseableHttpResponse response = (CloseableHttpResponse) client
                .execute(httpPost, (HttpContext) null);

            handler.post(() -> {
                if (response.getStatusLine().getStatusCode() == HttpsURLConnection.HTTP_OK) {
                    Intent intent = new Intent(activity, SuccessActivity.class);
                    activity.startActivity(intent);
                }
                else {
                    Toast.makeText(activity, activity.getString(R.string.error_info_unknownormail), Toast.LENGTH_LONG)
                            .show();
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String stringifyMap(Map<String, Object> data) {
        return new JSONObject(data).toString();
    }
}
