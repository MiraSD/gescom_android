package com.example.gescom_mobile.creators;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gescom_mobile.R;
import com.example.gescom_mobile.helpers.AppMenu;
import com.example.gescom_mobile.helpers.RequestHandler;

import org.json.JSONObject;

public class UtilisateurAdd   extends AppMenu {

    final String stringUrl = "http://192.168.1.3:8000/api/utilisateur/";
    Button submit;
    EditText nom;
    EditText prenom;
    EditText email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilisateur_add);

        nom = (EditText) findViewById(R.id.utilisateurName);
        prenom = findViewById(R.id.utilisateurprenom);
        email = findViewById(R.id.utilemail);




        String[] taxes = {"TVA VENTE 19%"};
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<String> staticAdapter =  new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, taxes);



        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);





        String[] categories = {"F001"};
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<String> categoryAdapter =  new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, categories);


    }

    public class RequestAsync extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("nom", strings[0]);
                postDataParams.put("prixht", strings[1]);
                postDataParams.put("email", strings[1]);


                return RequestHandler.sendPost(stringUrl,postDataParams);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s!=null){
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        }
    }


}
