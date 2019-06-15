package com.example.gescom_mobile.model;

public class Partner {
    public int id;
    public String nom;
    public String prenom;
    public String matFiscal;
    public String adress;
    public int tel;
    public int type;

    public Partner(int id, String nom, String prenom, String matFiscal, String adress, int tel, int type) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.matFiscal = matFiscal;
        this.adress = adress;
        this.tel = tel;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getMatFiscal() {
        return matFiscal;
    }

    public void setMatFiscal(String matFiscal) {
        this.matFiscal = matFiscal;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
