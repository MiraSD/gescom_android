package com.example.gescom_mobile.lists;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gescom_mobile.R;
import com.example.gescom_mobile.UtilisateurAdd;
import com.example.gescom_mobile.UtilisateurSingle;
import com.example.gescom_mobile.adapters.UtilisateurAdapter;
import com.example.gescom_mobile.helpers.AppMenu;
import com.example.gescom_mobile.loader.UtlilisateurLoader;
import com.example.gescom_mobile.model.Utilisateur;


import java.util.ArrayList;
import java.util.List;

public class UtilisateurList extends AppMenu implements LoaderManager.LoaderCallbacks<List<Utilisateur>> {
    UtilisateurAdapter utilisateurAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utilisateur);


        FloatingActionButton fabuti = (FloatingActionButton) findViewById(R.id.fabuti);
        fabuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UtilisateurAdd.class);

                startActivity(intent);
            }
        });


        utilisateurAdapter = new UtilisateurAdapter(this, new ArrayList<Utilisateur>());
        ListView utilisateurListView = (ListView) findViewById(R.id.utilisateur);
        utilisateurListView.setAdapter(utilisateurAdapter);
        getSupportLoaderManager().initLoader(1, null, this).forceLoad();

        utilisateurListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(UtilisateurList.this, UtilisateurSingle.class);
                i.putExtra("position",String.valueOf(utilisateurAdapter.getItem(position).getId()));

                startActivity(i);
            }
        });
    }
    @Override
    public Loader<List<Utilisateur>> onCreateLoader(int id, Bundle args) {
        return new UtlilisateurLoader(UtilisateurList.this);
    }
    @Override
    public void onLoadFinished(Loader<List<Utilisateur>> loader, List<Utilisateur> data) {
        utilisateurAdapter.setUtilisateur(data);
    }
    @Override
    public void onLoaderReset(Loader<List<Utilisateur>> loader) {
        utilisateurAdapter.setUtilisateur(new ArrayList<Utilisateur>());
    }


}
