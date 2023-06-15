package com.example.saes4_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.btn_fetch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetch();
            }
        });

        LinearLayout collapseCatergorieLayout = (LinearLayout) findViewById(R.id.categorie_collapse_parent);
        LinearLayout collapseSsCatergorieLayout = (LinearLayout) findViewById(R.id.sscategorie_collapse_parent);
        LinearLayout collapseSsssCatergorieLayout = (LinearLayout) findViewById(R.id.sssscategorie_collapse_parent);

        collapseCatergorieLayout.setOnClickListener(new CollapseLayoutListener(
                (FrameLayout) findViewById(R.id.categorie_collapse_mainLayout),
                (HorizontalScrollView) findViewById(R.id.categorie_collapse_child_horizontalScrollView),
                (ImageView) findViewById(R.id.categorie_collapse_arrow)
        ));
        collapseSsCatergorieLayout.setOnClickListener(new CollapseLayoutListener(
                (FrameLayout) findViewById(R.id.sscategorie_collapse_mainLayout),
                (HorizontalScrollView) findViewById(R.id.sscategorie_collapse_child_horizontalScrollView),
                (ImageView) findViewById(R.id.sscategorie_collapse_arrow)
        ));
        collapseSsssCatergorieLayout.setOnClickListener(new CollapseLayoutListener(
                (FrameLayout) findViewById(R.id.sssscategorie_collapse_mainLayout),
                (HorizontalScrollView) findViewById(R.id.sssscategorie_collapse_child_horizontalScrollView),
                (ImageView) findViewById(R.id.sssscategorie_collapse_arrow)
        ));
    }

    private void fetch() {
        TextView debugZone = (TextView) findViewById(R.id.debug_zone);
        AsyncTask<String, Void, JSONArray> task = new RetreiveAlimentTask(this, debugZone);
        task.execute();
    }
}