package fr.debrisgrollaud.gestionsdesstock;

public class Stockage {

    private int id;
    private String nom;
    private String lieu;
    private int dateAjout;

    //constructeurs
    public Stockage(int id, String nom, String lieu, int dateAjout) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.dateAjout = dateAjout;
    }

    public Stockage(String nom, String lieu, int dateAjout) {
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

    public int getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(int dateAjout) {
        this.dateAjout = dateAjout;
    }


}
