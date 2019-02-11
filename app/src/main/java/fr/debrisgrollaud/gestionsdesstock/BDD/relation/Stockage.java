package fr.debrisgrollaud.gestionsdesstock.BDD.relation;

import fr.debrisgrollaud.gestionsdesstock.lieu.ListLieu;

//Fichier de relation BDD pour les Stockage
public class Stockage {

    private int id;
    private String nom;
    private String lieu;
    private String dateAjout;

    //constructeurs
    public Stockage(int id, String nom, String lieu, String dateAjout) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.dateAjout = dateAjout;
    }

    public Stockage(String nom, String lieu, String dateAjout) {
        this.nom = nom;
        this.lieu = lieu;
        this.dateAjout = dateAjout;
    }

    //getters ans setters
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

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
    }


}
