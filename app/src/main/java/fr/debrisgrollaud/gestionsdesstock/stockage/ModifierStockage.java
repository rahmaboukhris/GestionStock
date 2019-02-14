package fr.debrisgrollaud.gestionsdesstock.stockage;

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

        nom_text.setText(stockage.getNom());

        MainActivity.BDD.open();

        Cursor cursor = MainActivity.BDD.select("lieu", "id == " + extras.getString("lieu"));

        ArrayList<String> arraySpinner = new ArrayList<>();

        lieux.clear();

        if (cursor != null) {
            cursor.moveToFirst();
            Lieu lieu = cursotToLieu(cursor);
            arraySpinner.add(lieu.getAdresse());
            lieux.add(lieu);
        }

        cursor = MainActivity.BDD.select("lieu");

        if (cursor != null){
            if (cursor.getCount() > 1){
                while (cursor.moveToNext()) {
                    Lieu lieu = cursotToLieu(cursor);

                    if (!String.valueOf(lieu.getId()).equals(extras.getString("lieu"))){
                        arraySpinner.add(lieu.getAdresse());
                        lieux.add(lieu);
                    }
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

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("nom", nom_text.getText().toString());
        hashMap.put("lieu", String.valueOf(lieux.get((int) lieu.getSelectedItemId()).getId()));
        hashMap.put("dateAjout", formattedDate);

        MainActivity.BDD.open();

        MainActivity.BDD.update(Id, "stockage", hashMap);

        Toast.makeText(MainActivity.Instance, R.string.app_tost_stockage_modifier,
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
