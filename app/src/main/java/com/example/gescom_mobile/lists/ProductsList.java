package com.example.gescom_mobile.lists;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gescom_mobile.ProductSingle;
import com.example.gescom_mobile.creators.ProductsAdd;
import com.example.gescom_mobile.R;
import com.example.gescom_mobile.adapters.ProductAdapter;
import com.example.gescom_mobile.helpers.AppMenu;
import com.example.gescom_mobile.loader.ProductLoader;
import com.example.gescom_mobile.model.Product;


import java.util.ArrayList;
import java.util.List;

public class ProductsList extends AppMenu implements LoaderManager.LoaderCallbacks<List<Product>> {
    ProductAdapter prodAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProductsAdd.class);

                startActivity(intent);
            }
        });


        prodAdapter = new ProductAdapter(this, new ArrayList<Product>());
        ListView productListView = (ListView) findViewById(R.id.products);
        productListView.setAdapter(prodAdapter);
        getSupportLoaderManager().initLoader(1, null, this).forceLoad();

        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(ProductsList.this, ProductSingle.class);
                i.putExtra("position",String.valueOf(prodAdapter.getItem(position).getPrid()));
                startActivity(i);
            }
        });
    }
    @Override
    public Loader<List<Product>> onCreateLoader(int id, Bundle args) {
        return new ProductLoader(ProductsList.this);
    }
    @Override
    public void onLoadFinished(Loader<List<Product>> loader, List<Product> data) {
        prodAdapter.setProducts(data);
    }
    @Override
    public void onLoaderReset(Loader<List<Product>> loader) {
        prodAdapter.setProducts(new ArrayList<Product>());
    }



}
