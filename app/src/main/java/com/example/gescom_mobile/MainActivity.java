package com.example.gescom_mobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gescom_mobile.helpers.RequestHandler;
import com.example.gescom_mobile.lists.ProductsList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {

    public static final int LOGIN_REQUEST_CODE = 0;

    private Button bLogin;
    private Button configuration ;
    private EditText etEmailAddress, etPassword;
    String stringUrl;
    Context mContext;



    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_main);
        this.bLogin = (Button) this.findViewById(R.id.submit);
        this.configuration = (Button) this.findViewById(R.id.configuration);
        this.etEmailAddress = (EditText) this.findViewById(R.id.username);
        this.etPassword = (EditText) this.findViewById(R.id.password);
        mContext = this;


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String url = preferences.getString("adresseIp", "127.0.0.1");
        String port = preferences.getString("port", "80");
        String suffix = preferences.getString("suffix", "/api/");
        String user = preferences.getString("userid", "/api/");

        if (user != null || user != ""){
            Intent intent = new Intent(MainActivity.this, ProductsList.class);
            startActivity(intent);
        }

        stringUrl = "http://" + url + ":" + port + suffix + "login";

        this.bLogin.setOnClickListener(new View.OnClickListener() {
            /*
             * (non-Javadoc)
             * @see android.view.View.OnClickListener#onClick(android.view.View)
             */
            @Override
            public void onClick(final View v) {

                try {
                    new MainActivity.RequestAsync(mContext).execute(stringUrl,etEmailAddress.getText().toString(),etPassword.getText().toString()).get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });


        this.configuration.setOnClickListener(new View.OnClickListener() {
            /*
             * (non-Javadoc)
             * @see android.view.View.OnClickListener#onClick(android.view.View)
             */
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(MainActivity.this, Configuration.class);
                startActivity(intent);
            }
        });
    }

    public class RequestAsync extends AsyncTask<String, Void, String> {

        Context context;

        public RequestAsync(Context context)
        {
            this.context=context;
        }


        @Override
        protected String doInBackground(String... strings) {
            try {
                JSONObject params = new JSONObject();
                try {
                    params.put("username", strings[1]);
                    params.put("password", strings[2]);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("BOURAOUI url",strings[0]);
                String res  =  RequestHandler.sendPost(strings[0],params);
                return res;
            } catch (Exception e) {


                Log.i("BOURAOUI",e.getMessage());
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);
            try {


                    JSONObject jsonObject = new JSONObject(result);
                    if ((boolean) jsonObject.get("name"))
                    {
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                        JSONObject user = new JSONObject(result);
                        preferences.getString("userid", jsonObject.get("user")+ " ");
                        Intent intent = new Intent(MainActivity.this, ProductsList.class);
                        startActivity(intent);
                    }


            } catch (JSONException | NullPointerException e) {
                Toast.makeText(context,
                        "Error...",
                        Toast.LENGTH_SHORT).show();
            }



        }
    }




}
