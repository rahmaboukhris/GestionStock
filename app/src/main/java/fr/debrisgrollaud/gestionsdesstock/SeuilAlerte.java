package fr.debrisgrollaud.gestionsdesstock;

public class SeuilAlerte {

    private String alerte;
    private String critique;

    //constructeurs
    public SeuilAlerte(String alerte, String critique) {
        this.alerte = alerte;
        this.critique = critique;
    }

    public String getAlerte() {
        return alerte;
    }

    public void setAlerte(String alerte) {
        this.alerte = alerte;
    }

    public String getCritique() {
        return critique;
    }

    public void setCritique(String critique) {
        this.critique = critique;
    }
}
