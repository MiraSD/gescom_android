package com.example.gescom_mobile.loader;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.gescom_mobile.helpers.RequestHandler;
import com.example.gescom_mobile.model.Depot;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DepotLoader extends AsyncTaskLoader<List<Depot>> {
    public DepotLoader(Context context) {
        super(context);
    }


    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
    String url = preferences.getString("adresseIp", "127.0.0.1");
    String port = preferences.getString("port", "80");
    String suffix = preferences.getString("suffix", "/api/");

    final String stringUrl = "http://" + url + ":" + port + suffix + "depot";


    @Override
    public List<Depot> loadInBackground() {
        String result;
        String inputLine;
        List<Depot> list = new ArrayList<Depot>();
        try {
            result = RequestHandler.sendGet(stringUrl);

            JSONArray DepotArray = new JSONArray(result);

            for (int i = 0; i < DepotArray.length(); i++) {
                Log.i("Bouraoui", String.valueOf(DepotArray.getJSONObject(i)));
                list.add(new Depot(DepotArray.getJSONObject(i).getInt("id"),
                        DepotArray.getJSONObject(i).getString("nom")));

            }


        } catch (ProtocolException e) {
            Log.i("Bouraoui", "1");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            Log.i("Bouraoui", "2");
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("Bouraoui", "3");
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;
    }
}
