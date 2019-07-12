package com.example.gescom_mobile.creators;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gescom_mobile.R;
import com.example.gescom_mobile.helpers.AppMenu;
import com.example.gescom_mobile.helpers.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class DepotsAdd extends AppMenu {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    Button submitDepot;
    String stringUrl;
    EditText depotName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.depots_add);

        submitDepot = findViewById(R.id.submitDepot);
        depotName = findViewById(R.id.depotName);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String url = preferences.getString("adresseIp", "127.0.0.1");
        String port = preferences.getString("port", "80");
        String suffix = preferences.getString("suffix", "/api/");

        stringUrl = "http://" + url + ":" + port + suffix + "depot";

        this.submitDepot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                try {
                    new DepotsAdd.RequestAsync().execute(stringUrl,depotName.getText().toString()).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public class RequestAsync extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            try {
                JSONObject params = new JSONObject();
                try {
                    params.put("nom", strings[1]);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return RequestHandler.sendPost(strings[0],params);
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }


}
