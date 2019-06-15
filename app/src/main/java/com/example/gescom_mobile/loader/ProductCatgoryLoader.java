package com.example.gescom_mobile.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.gescom_mobile.model.ProductCategory;


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

public class ProductCatgoryLoader extends AsyncTaskLoader<List<ProductCategory>> {
    public ProductCatgoryLoader(Context context) {
        super(context);
    }

    final String stringUrl = "http://localhost:8000/api/catgeory/";


    @Override
    public List<ProductCategory>  loadInBackground(){
        String result;
        String inputLine;
        List<ProductCategory> list = new ArrayList<ProductCategory>();
        try {
            //Create a URL object holding our url
            URL myUrl = new URL(stringUrl);
            //Create a connection
            HttpURLConnection connection =(HttpURLConnection)
                    myUrl.openConnection();
            //Set methods and timeouts
            connection.setRequestMethod("GET");
            connection.setReadTimeout(1500);
            connection.setConnectTimeout(1500);

            //Connect to our url
            connection.connect();

            //Create a new InputStreamReader
            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());
            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            //Check if the line we are reading is not null
            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            //Close our InputStream and Buffered reader
            reader.close();
            streamReader.close();
            //Set our result equal to our stringBuilder
            result = stringBuilder.toString();

            JSONArray productsArray = new JSONArray(result);

            for(int i = 0; i < productsArray.length(); i++){
                Log.i("Bouraoui", String.valueOf(productsArray.getJSONObject(i)));
                list.add(new ProductCategory(productsArray.getJSONObject(i).getInt("id"),
                        productsArray.getJSONObject(i).getString("name")));

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
