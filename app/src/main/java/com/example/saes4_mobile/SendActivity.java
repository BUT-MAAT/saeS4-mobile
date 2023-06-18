package com.example.saes4_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saes4_mobile.data.SurveyIsCompleted;
import com.example.saes4_mobile.listeners.SubmitButtonOnClickListener;

public class SendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (SurveyIsCompleted.isSurveyCompleted()) {
            Intent intent = new Intent(this, SuccessActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_send);

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new SubmitButtonOnClickListener(this));
    }
}
