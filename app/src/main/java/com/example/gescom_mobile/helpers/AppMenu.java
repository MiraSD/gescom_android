package com.example.gescom_mobile.helpers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gescom_mobile.Configuration;
import com.example.gescom_mobile.lists.DepotsList;
import com.example.gescom_mobile.lists.PartnersList;
import com.example.gescom_mobile.lists.ProductsList;
import com.example.gescom_mobile.R;

public class AppMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item4:
                Intent intent = new Intent(this, ProductsList.class);

                startActivity(intent);
                return true;
            case R.id.item5:
                Intent intentPartners = new Intent(this, PartnersList.class);
            case R.id.item13:
                Intent intentConfig = new Intent(this, Configuration.class);
            case R.id.item7:
                Intent intentDepot = new Intent(this, DepotsList.class);

                startActivity(intentDepot);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
