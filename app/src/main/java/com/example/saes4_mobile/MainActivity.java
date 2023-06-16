package com.example.saes4_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.saes4_mobile.fetchtasks.RetreiveCategoriesTask;
import com.example.saes4_mobile.listeners.CollapseLayoutListener;
import com.example.saes4_mobile.pillfactories.CategoryPillFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout collapseCatergorieLayout = (LinearLayout) findViewById(R.id.categorie_collapse_parent);
        LinearLayout collapseSsCatergorieLayout = (LinearLayout) findViewById(R.id.sscategorie_collapse_parent);
        LinearLayout collapseSsssCatergorieLayout = (LinearLayout) findViewById(R.id.sssscategorie_collapse_parent);

        ((TextView) findViewById(R.id.categorie_collapse_title)).setText(
                String.format(getString(R.string.catergorie_collapse_title), "Aucune")
        );
        ((TextView) findViewById(R.id.sscategorie_collapse_title)).setText(
                String.format(getString(R.string.sscatergorie_collapse_title), "Aucune")
        );
        ((TextView) findViewById(R.id.sssscategorie_collapse_title)).setText(
                String.format(getString(R.string.sssscatergorie_collapse_title), "Aucune")
        );

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

        fetchFirstCategories();
    }
    private void fetchFirstCategories() {
        LinearLayout categoryLinearLayout = (LinearLayout) findViewById(R.id.categorie_insert_layout);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new RetreiveCategoriesTask(this,
                getString(R.string.api_categories_all_path),
                categoryLinearLayout,
                new CategoryPillFactory(),
                new Handler(Looper.getMainLooper())));
    }
}