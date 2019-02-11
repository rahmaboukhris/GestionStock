package fr.debrisgrollaud.gestionsdesstock.BDD.relation;

//Fichier de relation BDD pour les item
public class Item {

    private int id;
    private String nom;
    private String quantite;
    private String reference;
    private Categorie categorie;
    private int dateAjout;
    private String fournisseur;
    private SeuilAlerte seuilAlerte;

    //constructeurs
    public Item(int id, String nom, String quantite, String reference, Categorie categorie, int dateAjout, String fournisseur, SeuilAlerte seuilAlerte) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.reference = reference;
        this.categorie = categorie;
        this.dateAjout = dateAjout;
        this.fournisseur = fournisseur;
        this.seuilAlerte = seuilAlerte;
    }

    public Item(String nom, String quantite, String reference, Categorie categorie, int dateAjout, String fournisseur, SeuilAlerte seuilAlerte) {
        this.nom = nom;
        this.quantite = quantite;
        this.reference = reference;
        this.categorie = categorie;
        this.dateAjout = dateAjout;
        this.fournisseur = fournisseur;
        this.seuilAlerte = seuilAlerte;
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
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

    public SeuilAlerte getSeuilAlerte() {
        return seuilAlerte;
    }

    public void setSeuilAlerte(SeuilAlerte seuilAlerte) {
        this.seuilAlerte = seuilAlerte;
    }
}
