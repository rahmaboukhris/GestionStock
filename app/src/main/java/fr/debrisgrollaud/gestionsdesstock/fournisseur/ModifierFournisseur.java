package fr.debrisgrollaud.gestionsdesstock.fournisseur;

import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Fournisseur;
import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Lieu;
import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Stockage;
import fr.debrisgrollaud.gestionsdesstock.MainActivity;
import fr.debrisgrollaud.gestionsdesstock.R;

//Modif d'un fournisseur
public class ModifierFournisseur extends AjoutFournisseur {

    private String Id;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name_modifier_fournisseur);
        Fournisseur fournisseur;

        Bundle extras = getIntent().getExtras();
        if (extras == null) return;

        //Recuperation des extras
        Id = extras.getString("id");

        fournisseur = new Fournisseur(extras.getString("nom"), extras.getString("lieu"),extras.getString("email"),extras.getString("telephone"));

        //Definition des texte dans la view
        nom_text.setText(fournisseur.getNom());
        email_text.setText(fournisseur.getEmail());
        telephone_text.setText(fournisseur.getTelephone());

        //Open BDD
        MainActivity.BDD.open();

        //Recuperation du lieu avec l'id
        Cursor cursor = MainActivity.BDD.select("lieu", "id == " + extras.getString("lieu"));

        ArrayList<String> arraySpinner = new ArrayList<>();

        //lieu pour le spiner
        lieux.clear();

        if (cursor != null) {
            cursor.moveToFirst();
            Lieu lieu = cursotToLieu(cursor);
            arraySpinner.add(lieu.getAdresse());
            lieux.add(lieu);
        }

        //Recuperation de tout les lieu
        cursor = MainActivity.BDD.select("lieu");

        if (cursor != null){
            if (cursor.getCount() > 1){
                while (cursor.moveToNext()) {
                    Lieu lieu = cursotToLieu(cursor);

                    //Ajout du lieu si cela n'ais pas celuis de basse
                    if (!String.valueOf(lieu.getId()).equals(extras.getString("lieu"))){
                        arraySpinner.add(lieu.getAdresse());
                        lieux.add(lieu);
                    }
                }
            }
        }

        //envoie au spiner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        lieu.setAdapter(adapter);
    }

    //Update du fournisseur
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void addFournisseur() {

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("nom", nom_text.getText().toString());
        hashMap.put("email", email_text.getText().toString());
        hashMap.put("telephone", telephone_text.getText().toString());
        hashMap.put("lieu", String.valueOf(lieux.get((int) lieu.getSelectedItemId()).getId()));

        MainActivity.BDD.open();

        MainActivity.BDD.update(Id,"fournisseur", hashMap);

        Toast.makeText(MainActivity.Instance, R.string.app_tost_modifier_forunisseur,
                Toast.LENGTH_LONG).show();

        MainActivity.setActivity(MainActivity.class);
    }

    private Lieu cursotToLieu(Cursor cursor) {
        String id = cursor.getString(0);
        String ville = cursor.getString(1);
        String rue = cursor.getString(2);
        String numero = cursor.getString(3);
        String postal = cursor.getString(4);

        return new Lieu(Integer.parseInt(id), numero, rue, ville, postal);
    }
}
