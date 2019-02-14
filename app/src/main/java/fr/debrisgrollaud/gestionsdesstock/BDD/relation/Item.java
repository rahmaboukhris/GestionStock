package fr.debrisgrollaud.gestionsdesstock.BDD.relation;

//Fichier de relation BDD pour les item
public class Item {

    private int id;
    private String nom;
    private String quantite;
    private String stockage;
    private String categorie;
    private String dateAjout;
    private String fournisseur;
    private SeuilAlerte seuilAlerte;

    //constructeurs
    public Item(int id, String nom, String quantite, String categorie, String stockage, String dateAjout, String fournisseur, SeuilAlerte seuilAlerte) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.categorie = categorie;
        this.dateAjout = dateAjout;
        this.fournisseur = fournisseur;
        this.seuilAlerte = seuilAlerte;
        this.stockage = stockage;
    }

    public Item(String nom, String quantite, String categorie, String stockage, String dateAjout, String fournisseur, SeuilAlerte seuilAlerte) {
        this.nom = nom;
        this.quantite = quantite;
        this.categorie = categorie;
        this.dateAjout = dateAjout;
        this.fournisseur = fournisseur;
        this.seuilAlerte = seuilAlerte;
        this.stockage = stockage;
    }

    //getters and setters
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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public SeuilAlerte getSeuilAlerte() {
        return seuilAlerte;
    }

    public void setSeuilAlerte(SeuilAlerte seuilAlerte) {
        this.seuilAlerte = seuilAlerte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStockage() {
        return stockage;
    }

    public void setStockage(String stockage) {
        this.stockage = stockage;
    }
}
