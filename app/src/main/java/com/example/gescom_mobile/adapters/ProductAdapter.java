package com.example.gescom_mobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gescom_mobile.R;
import com.example.gescom_mobile.model.Product;


import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Product> products = new ArrayList<Product>();
    public ProductAdapter(Context context, List<Product> products) {
        this.products = products;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Product prd = (Product) getItem(position);
        if (view == null) {
            view = inflater.inflate(R.layout.productsdata, null);
        }
        TextView prodid = (TextView) view.findViewById(R.id.prodid);
        prodid.setText(String.valueOf(prd.prid));
        TextView prodname = (TextView) view.findViewById(R.id.prodname);
        prodname.setText(prd.name);
        return view;
    }
    @Override
    public Product getItem(int position) {
        return products.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getCount() {
        return products.size();
    }
    public void setProducts(List<Product> data) {
        products.addAll(data);
        notifyDataSetChanged();
    }
}