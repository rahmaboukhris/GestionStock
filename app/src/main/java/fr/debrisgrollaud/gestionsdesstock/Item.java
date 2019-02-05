package fr.debrisgrollaud.gestionsdesstock;

public class Item {

    private int id;
    private String nom;
    private String quantite;
    private String reference;
    private String categorie;
    private int dateAjout;
    private String fournisseur;

    public Item(int id, String nom, String quantite, String reference, String categorie, int dateAjout, String fournisseur) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.reference = reference;
        this.categorie = categorie;
        this.dateAjout = dateAjout;
        this.fournisseur = fournisseur;
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

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(int dateAjout) {
        this.dateAjout = dateAjout;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }
}
