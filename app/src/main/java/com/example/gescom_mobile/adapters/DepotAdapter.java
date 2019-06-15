package com.example.gescom_mobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gescom_mobile.R;
import com.example.gescom_mobile.model.Depot;


import java.util.ArrayList;
import java.util.List;

public class DepotAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Depot> depot = new ArrayList<Depot>();
    public DepotAdapter(Context context, List<Depot> products) {
        this.depot = depot;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Depot depot = (Depot) getItem(position);
        if (view == null) {
            view = inflater.inflate(R.layout.depotdata, null);
        }
        TextView depotid = (TextView) view.findViewById(R.id.depotid);
        depotid.setText(String.valueOf(depot.id));
        TextView depotname = (TextView) view.findViewById(R.id.depotname);
        depotname.setText(depot.nom);
        return view;
    }
    @Override
    public Depot getItem(int position) {
        return depot.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getCount() {
        return depot.size();
    }
    public void setDepot(List<Depot> data) {
        depot.addAll(data);
        notifyDataSetChanged();
    }

}
