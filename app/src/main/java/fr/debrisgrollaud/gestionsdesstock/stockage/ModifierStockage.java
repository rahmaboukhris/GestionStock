package fr.debrisgrollaud.gestionsdesstock.stockage;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

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
