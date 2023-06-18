package com.example.saes4_mobile.listeners;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.example.saes4_mobile.R;
import com.example.saes4_mobile.data.SelectedAliments;
import com.example.saes4_mobile.apitasks.SendResultsTask;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SubmitButtonOnClickListener implements View.OnClickListener {

    private Activity activity;

    public SubmitButtonOnClickListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Map<String, Object> data = new HashMap<>();

        data.put("id_personne", null);
        data.put("nom", ((TextInputEditText) activity.findViewById(R.id.send_inputLastName)).getText().toString());
        data.put("prenom", ((TextInputEditText) activity.findViewById(R.id.send_inputName)).getText().toString());
        data.put("mail", ((TextInputEditText) activity.findViewById(R.id.send_inputMail)).getText().toString());
        data.put("code_postal", ((TextInputEditText) activity.findViewById(R.id.send_inputZipCode)).getText().toString());
        data.put("ville", ((TextInputEditText) activity.findViewById(R.id.send_inputCity)).getText().toString());

        data.put("liste_aliments", new ArrayList<>());
        for(int alimentId : SelectedAliments.getAlimentIds()) {
            Map<String, Integer> aliment = new HashMap<>();
            aliment.put("id_aliment", alimentId);
            ((List<Map<String, Integer>>) data.get("liste_aliments")).add(aliment);
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new SendResultsTask(activity,
                activity.getString(R.string.api_survey_send_path),
                data, new Handler(Looper.getMainLooper())));
    }
}
