package fr.debrisgrollaud.gestionsdesstock;

public class Fournisseur {

    private int id;
    private String nom;
    private String lieu;
    private String email;
    private int telephone;

    //constructeurs
    public Fournisseur(int id, String nom, String lieu, String email, int telephone) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.email = email;
        this.telephone = telephone;
    }

    public Fournisseur(String nom, String lieu, String email, int telephone) {
        this.nom = nom;
        this.lieu = lieu;
        this.email = email;
        this.telephone = telephone;
    }

    //getters and setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
}
