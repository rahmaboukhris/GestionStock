package fr.debrisgrollaud.gestionsdesstock.categorie;

import android.os.Bundle;

import java.util.HashMap;

import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Categorie;
import fr.debrisgrollaud.gestionsdesstock.MainActivity;
import fr.debrisgrollaud.gestionsdesstock.R;

public class ModifierCategorie extends AjoutCategorie{
    private String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Modifier une catégorie");
        Categorie categorie;

        Bundle extras = getIntent().getExtras();
        if (extras == null) return;

        Id = extras.getString("id");

        categorie = new Categorie(extras.getString("nom"));

        if (categorie.toString().length() <= 0) {
            MainActivity.setActivity(ListCategorie.class);
            return;
        }

        nom_text.setText(categorie.getNom());
    }

    @Override
    protected void addCategorie() {

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("nom", nom_text.getText().toString());

        MainActivity.BDD.open();

        MainActivity.BDD.update(Id,"categorie", hashMap);

        MainActivity.setActivity(ListCategorie.class);
    }
}
