package com.example.saes4_mobile.listeners;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.saes4_mobile.R;
import com.example.saes4_mobile.apitasks.SendResultsTask;
import com.example.saes4_mobile.data.SelectedAliments;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SubmitButtonOnClickListener implements View.OnClickListener {

    private Activity activity;
    private Toast toast;

    public SubmitButtonOnClickListener(Activity activity) {
        this.activity = activity;
        this.toast = Toast.makeText(activity, "message", Toast.LENGTH_SHORT);
    }

    @Override
    public void onClick(View v) {
        toast.cancel();
        Map<String, Object> data = new HashMap<>();

        Editable name = ((TextInputEditText) activity.findViewById(R.id.send_inputName)).getText();
        Editable lastname = ((TextInputEditText) activity.findViewById(R.id.send_inputLastName)).getText();
        Editable mail = ((TextInputEditText) activity.findViewById(R.id.send_inputMail)).getText();
        Editable zipCode = ((TextInputEditText) activity.findViewById(R.id.send_inputZipCode)).getText();
        Editable city = ((TextInputEditText) activity.findViewById(R.id.send_inputCity)).getText();

        Log.d("TEST", name.toString());

        if (name.toString().equals("")
            || lastname.toString().equals("")
            || mail.toString().equals("")
            || zipCode.toString().equals("")
            || city.toString().equals("")) {

            toast = Toast.makeText(activity, activity.getString(R.string.error_info_notallfields), Toast.LENGTH_SHORT);
            toast.show();
        }

        else if(!mail.toString().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            toast = Toast.makeText(activity, activity.getString(R.string.error_info_mailwrongformat), Toast.LENGTH_SHORT);
            toast.show();
        }

        else if (zipCode.toString().length() != 5) {
            toast = Toast.makeText(activity, activity.getString(R.string.error_info_zipcode), Toast.LENGTH_SHORT);
            toast.show();
        }

        else {
            Log.d("TEST", "PASSES HERE");
            data.put("id_personne", null);
            data.put("nom", name.toString());
            data.put("prenom", lastname.toString());
            data.put("mail", mail.toString());
            data.put("code_postal", zipCode.toString());
            data.put("ville", city.toString());

            data.put("liste_aliments", new ArrayList<>());
            for (int alimentId : SelectedAliments.getAlimentIds()) {
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
}
