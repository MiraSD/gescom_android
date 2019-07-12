package com.example.gescom_mobile.creators;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.gescom_mobile.R;
import com.example.gescom_mobile.helpers.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class PartnersAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partners_add);
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
