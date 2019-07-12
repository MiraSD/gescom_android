package com.example.gescom_mobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gescom_mobile.R;
import com.example.gescom_mobile.model.Partner;


import java.util.ArrayList;
import java.util.List;

public class PartnerAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Partner> partner = new ArrayList<Partner>();
    public PartnerAdapter(Context context, List<Partner> partner) {
        this.partner =partner;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Partner partner = (Partner ) getItem(position);
        if (view == null) {
            view = inflater.inflate(R.layout.partnerdata, null);
        }

        TextView partnername = (TextView) view.findViewById(R.id.partnername);
        partnername.setText(partner.nom);
        TextView partnerlastname = (TextView) view.findViewById(R.id.partnerlastname);
        partnerlastname.setText(partner.prenom);
        TextView partnermatFiscal = (TextView) view.findViewById(R.id.partnermatFiscal);
        partnermatFiscal.setText(partner.matFiscal);



        return view;
    }
    @Override
    public Partner getItem(int position) {
        return partner.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getCount() {
        return partner.size();
    }
    public void setPartner(List<Partner> data) {
        partner.addAll(data);
        notifyDataSetChanged();
    }


}
