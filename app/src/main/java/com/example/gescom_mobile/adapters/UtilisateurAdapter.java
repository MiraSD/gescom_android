package com.example.gescom_mobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gescom_mobile.R;
import com.example.gescom_mobile.model.Product;
import com.example.gescom_mobile.model.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Utilisateur> utilisateur = new ArrayList<Utilisateur>();
    public UtilisateurAdapter(Context context, List<Utilisateur> utilisateur) {
        this.utilisateur = utilisateur;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Utilisateur utilisateur = (Utilisateur ) getItem(position);
        if (view == null) {
            view = inflater.inflate(R.layout.utilisateurdata, null);
        }
        TextView utilisateurid = (TextView) view.findViewById(R.id.utilisateurid);
        utilisateurid.setText(String.valueOf(utilisateur.id));
        TextView utilisateurname = (TextView) view.findViewById(R.id.utilisateurname);
        utilisateurname.setText(utilisateur.nom);
        TextView utilisateurlastname = (TextView) view.findViewById(R.id.utilisateurlastname);
        utilisateurlastname.setText(utilisateur.prenom);
        return view;
    }
    @Override
    public Utilisateur getItem(int position) {
        return utilisateur.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getCount() {
        return utilisateur.size();
    }
    public void setUtilisateur(List<Utilisateur> data) {
        utilisateur.addAll(data);
        notifyDataSetChanged();
    }

}
