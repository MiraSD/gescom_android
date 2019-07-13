package com.example.gescom_mobile.singles;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gescom_mobile.MainActivity;
import com.example.gescom_mobile.R;
import com.example.gescom_mobile.helpers.AppMenu;
import com.example.gescom_mobile.helpers.RequestHandler;
import com.example.gescom_mobile.lists.ProductsList;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class DepotSingle extends AppMenu {

    TextView depotName;
    Context mContext;
    String stringUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depot_single);
        depotName = findViewById(R.id.depotName);

        mContext = this;


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String url = preferences.getString("adresseIp", "127.0.0.1");
        String port = preferences.getString("port", "80");
        String suffix = preferences.getString("suffix", "/api/");


        stringUrl = "http://" + url + ":" + port + suffix + "depot/1";


    }

    public class RequestAsync extends AsyncTask<String, Void, String> {

        Context context;

        public RequestAsync(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {


                String res = RequestHandler.sendGet(strings[0]);
                return res;
            } catch (Exception e) {
                Log.i("BOURAOUI", e.getMessage());
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            try {
                
                JSONObject depot = new JSONObject(result);
                depotName.setText(" " + depot.get("nom"));


            } catch (JSONException | NullPointerException e) {
                Toast.makeText(context,
                        "Error...",
                        Toast.LENGTH_SHORT).show();
            }


        }
    }


}

