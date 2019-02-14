package fr.debrisgrollaud.gestionsdesstock.stockage;

import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Lieu;
import fr.debrisgrollaud.gestionsdesstock.MainActivity;
import fr.debrisgrollaud.gestionsdesstock.R;

//Ajout d'un stockage
@RequiresApi(api = Build.VERSION_CODES.N)
public class AjoutStockage extends AppCompatActivity {

    protected TextInputLayout nom;
    protected Spinner lieu;
    protected EditText nom_text;
    protected ArrayList<Lieu> lieux = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_stockage);
        setTitle("Ajout d'un stockage");

        Button valider = findViewById(R.id.button_ajout_stockage);

        nom = findViewById(R.id.input_ajoutStockage_nom);
        lieu = findViewById(R.id.input_ajoutFournisseur_lieu);

        valider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onValide();
            }
        });

        nom_text = nom.getEditText();

        TextWatcher textWatcher = textWatcher();

        nom_text.addTextChangedListener(textWatcher);

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
                arraySpinner.add(adresse);
                lieux.add(lieu);
            }

        }

        if (arraySpinner.isEmpty()){
            arraySpinner.add("Acunne Adresse !");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);

        lieu.setAdapter(adapter);
    }

    protected void onValide() {

        boolean error = false;

        if (nom_text.length() == 0) {
            nom.setError("Merci de remplir le champ");
            error = true;
        }

        if (lieu.getSelectedItem().toString().length() == 0 || lieu.getSelectedItem().toString().equals("Acunne Adresse !")) {
            error = true;
            Toast.makeText(this, "Merci de sélectionner un lieu", Toast.LENGTH_LONG).show();
        }

        if (!error) {
            addStockage();
        }

    }

    protected void addStockage() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("nom", nom_text.getText().toString());
        hashMap.put("lieu", String.valueOf(lieux.get((int) lieu.getSelectedItemId()).getId()));
        hashMap.put("dateAjout", formattedDate);


        MainActivity.BDD.open();

        MainActivity.BDD.insert("stockage", hashMap);

        nom_text.setText("");

        Toast.makeText(MainActivity.Instance, R.string.app_tost_ajouter_stockage,
                Toast.LENGTH_LONG).show();
    }

    protected TextWatcher textWatcher() {
        return new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (nom.getEditText() == null) {
                    return;
                }

                if (nom.getEditText().getText().hashCode() == s.hashCode() && count != 0) {
                    nom.setError(null);
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }
}
