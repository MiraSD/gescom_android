package com.example.gescom_mobile.lists;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gescom_mobile.singles.DepotSingle;
import com.example.gescom_mobile.creators.PartnersAdd;
import com.example.gescom_mobile.R;
import com.example.gescom_mobile.adapters.DepotAdapter;
import com.example.gescom_mobile.helpers.AppMenu;
import com.example.gescom_mobile.loader.DepotLoader;
import com.example.gescom_mobile.model.Depot;

import java.util.ArrayList;
import java.util.List;


public class DepotsList extends AppMenu implements LoaderManager.LoaderCallbacks<List<Depot>> {
    DepotAdapter depotAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depot);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PartnersAdd.class);

                startActivity(intent);
            }
        });
        depotAdapter = new DepotAdapter(this, new ArrayList<Depot>());
        ListView depotListView = (ListView) findViewById(R.id.depots);
        depotListView.setAdapter(depotAdapter);
        getSupportLoaderManager().initLoader(1, null, this).forceLoad();

        depotListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(DepotsList.this, DepotSingle.class);
                i.putExtra("position",String.valueOf(depotAdapter.getItem(position).getId()));
                startActivity(i);
            }
        });
    }
    @Override
    public Loader<List<Depot>> onCreateLoader(int id, Bundle args) {
        return new DepotLoader(DepotsList.this);
    }
    @Override
    public void onLoadFinished(Loader<List<Depot>> loader, List<Depot> data) {
        depotAdapter.setDepot(data);
    }
    @Override
    public void onLoaderReset(Loader<List<Depot>> loader) {
        depotAdapter.setDepot(new ArrayList<Depot>());
    }

}


