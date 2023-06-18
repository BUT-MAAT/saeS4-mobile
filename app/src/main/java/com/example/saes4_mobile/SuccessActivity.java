package com.example.saes4_mobile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saes4_mobile.data.SurveyIsCompleted;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SurveyIsCompleted.surveyCompleted();
        setContentView(R.layout.activity_success);
    }
}
