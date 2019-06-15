package com.example.gescom_mobile.model;

public class Utilisateur {
    public Integer id;
    public String nom;
    public String prenom;



    public Utilisateur(Integer id, String nom,String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
