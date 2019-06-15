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

public class ProductsAdd extends AppMenu {

    final String stringUrl = "http://192.168.1.3:8000/api/produit/";
    Button submitProd;
    EditText productName;
    EditText prixHt;
    Spinner taxe;
    Spinner product_category;
    String taxeName;
    String categoryName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_add);

        taxe = (Spinner) findViewById(R.id.taxe);
        productName = findViewById(R.id.productName);
        prixHt = findViewById(R.id.prixHt);


        String[] taxes = {"TVA VENTE 19%"};
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<String> staticAdapter =  new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, taxes);



        // Specify the layout to use when the list of choices appears
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        taxe.setAdapter(staticAdapter);

        taxe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?>arg0, View view, int arg2, long arg3) {

                taxeName = taxe.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        product_category = findViewById(R.id.product_category);

        String[] categories = {"F001"};
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<String> categoryAdapter =  new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, categories);

        // Specify the layout to use when the list of choices appears
        categoryAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        product_category.setAdapter(categoryAdapter);

        product_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?>arg0, View view, int arg2, long arg3) {

                categoryName = product_category.getSelectedItem().toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        submitProd = findViewById(R.id.submitProd);

        submitProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new RequestAsync().execute(productName.getText().toString(),prixHt.getText().toString(),"1");
            }
        });

    }

    public class RequestAsync extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("nom", strings[0]);
                postDataParams.put("prixht", strings[1]);
                postDataParams.put("taxe_id", strings[2]);
                postDataParams.put("famille_produit_id", 1);


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
