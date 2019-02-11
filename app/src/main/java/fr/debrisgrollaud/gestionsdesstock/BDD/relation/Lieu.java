package fr.debrisgrollaud.gestionsdesstock.BDD.relation;

public class Lieu {

    private int id;
    private String ville;
    private String rue;
    private String numero;
    private String codePostal;

    public Lieu(int id, String numero, String rue, String ville, String codePostal) {
        this.id = id;
        this.ville = ville;
        this.rue = rue;
        this.numero = numero;
        this.codePostal = codePostal;
    }

    public Lieu(String numero, String rue, String ville, String codePostal) {
        this.ville = ville;
        this.rue = rue;
        this.numero = numero;
        this.codePostal = codePostal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String num) {
        this.numero = num;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getAdresse(){
        return getNumero() + " " + getRue() + " " + getVille() + " " + getCodePostal();
    }
}
