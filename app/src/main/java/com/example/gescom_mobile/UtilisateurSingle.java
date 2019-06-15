package com.example.gescom_mobile;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.gescom_mobile.helpers.AppMenu;
import com.example.gescom_mobile.helpers.RequestHandler;


import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class UtilisateurSingle extends AppMenu {

    TextView utilisateurName;
    String result;
    JSONObject obj;
    final String stringUrl = "http://192.168.1.3:8000/api/utilisateur/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilisateur_single);
        utilisateurName = findViewById(R.id.utilisateurName);
        try {
            Intent intent = getIntent();
            String id = intent.getStringExtra("position");
            result =  new RequestAsync().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        utilisateurName= findViewById(R.id.utilisateurName);

        try {

            obj = new JSONObject(result);
            utilisateurName.setText(obj.getString("name"));


        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + result + "\"");
        }





    }


    public class RequestAsync extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            try {
                //GET Request

                return RequestHandler.sendGet(stringUrl + strings[0]);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }
    }



}
