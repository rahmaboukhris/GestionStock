package fr.debrisgrollaud.gestionsdesstock.stockage;

import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Lieu;
import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Stockage;
import fr.debrisgrollaud.gestionsdesstock.MainActivity;
import fr.debrisgrollaud.gestionsdesstock.R;
import fr.debrisgrollaud.gestionsdesstock.stockage.ListStockage;

//Modif stockage
public class ModifierStockage extends AjoutStockage {

    private String Id;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name_modifier_stockage);
        Stockage stockage;

        Bundle extras = getIntent().getExtras();
        if (extras == null) return;

        Id = extras.getString("id");

        stockage = new Stockage(extras.getString("nom"), extras.getString("date"), extras.getString("lieu"));

        if (stockage.toString().length() <= 0) {
            MainActivity.setActivity(ListStockage.class);
            return;
        }

        nom_text.setText(stockage.getNom());
        date_text.setText(stockage.getDateAjout());

        MainActivity.BDD.open();

        Cursor cursor = MainActivity.BDD.select("lieu");

        ArrayList<String> arraySpinner = new ArrayList<>();

        if (cursor != null){

            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String ville = cursor.getString(1);
                String rue = cursor.getString(2);
                String numero = cursor.getString(3);
                String postal = cursor.getString(4);

                Lieu lieu = new Lieu(Integer.parseInt(id),numero,rue,ville,postal);
                String adresse = lieu.toString();

                if (String.valueOf(lieu.getId()) == extras.getString("lieu")){
                    arraySpinner.add(adresse);
                }
            }

            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String ville = cursor.getString(1);
                String rue = cursor.getString(2);
                String numero = cursor.getString(3);
                String postal = cursor.getString(4);

                Lieu lieu = new Lieu(Integer.parseInt(id),numero,rue,ville,postal);
                String adresse = lieu.toString();

                if (!String.valueOf(lieu.getId()).equals(extras.getString("lieu"))){
                    arraySpinner.add(adresse);
                }
            }

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        lieu.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void addStockage() {

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("nom", nom_text.getText().toString());
        hashMap.put("date", date_text.getText().toString());

        MainActivity.BDD.open();

        MainActivity.BDD.update(Id,"stockage", hashMap);

        MainActivity.setActivity(ListStockage.class);
    }
}
