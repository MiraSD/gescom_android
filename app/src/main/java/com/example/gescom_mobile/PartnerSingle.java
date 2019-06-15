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

public class PartnerSingle extends AppMenu {

    TextView partnerName;
    String result;
    JSONObject obj;
    final String stringUrl = "http://192.168.1.3:8000/api/partner/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_single);
        partnerName = findViewById(R.id.partnerName);

        try {
            Intent intent = getIntent();
            String id = intent.getStringExtra("position");
            result =  new RequestAsync().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        partnerName = findViewById(R.id.partnerName);

        try {

            obj = new JSONObject(result);
            partnerName.setText(obj.getString("name"));


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
