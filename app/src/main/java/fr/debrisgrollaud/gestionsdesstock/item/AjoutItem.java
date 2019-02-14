package fr.debrisgrollaud.gestionsdesstock.item;

import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Categorie;
import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Fournisseur;
import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Lieu;
import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Stockage;
import fr.debrisgrollaud.gestionsdesstock.MainActivity;
import fr.debrisgrollaud.gestionsdesstock.R;

//Ajout d'un item
public class AjoutItem extends AppCompatActivity {

    //Utilis
    protected TextInputLayout nom;
    protected TextInputLayout nombres;
    protected TextInputLayout alerte;
    protected TextInputLayout critique;
    protected EditText nom_text;
    protected EditText nombres_text;
    protected EditText alerte_text;
    protected EditText critique_text;
    protected Spinner categorie;
    protected Spinner fournisseur;
    protected Spinner stockage;
    protected ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
    protected ArrayList<Categorie> categories = new ArrayList<>();
    protected ArrayList<Stockage> stockages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_item);

        Button valider = findViewById(R.id.button_ajout_item);

        nom = findViewById(R.id.input_ajoutItem_nom);
        nombres = findViewById(R.id.input_ajoutItem_nombres);
        alerte = findViewById(R.id.input_ajoutItem_allerte);
        critique = findViewById(R.id.input_ajoutItem_critique);
        fournisseur = findViewById(R.id.spiner_ajoutItem_fournisseur);
        categorie = findViewById(R.id.spiner_ajoutItem_categorie);
        stockage = findViewById(R.id.spiner_ajoutItem_stockage);

        // Action on clique
        valider.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v) {
                onValide();
            }
        });

        //Get text
        nom_text = nom.getEditText();
        nombres_text = nombres.getEditText();
        alerte_text = alerte.getEditText();
        critique_text = critique.getEditText();

        //Text Watcher, on validate
        TextWatcher textWatcher = textWatcher();

        nom_text.addTextChangedListener(textWatcher);
        nombres_text.addTextChangedListener(textWatcher);
        alerte_text.addTextChangedListener(textWatcher);
        critique_text.addTextChangedListener(textWatcher);

        setSpiner();

    }

    //on validate on clique
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onValide() {

        boolean error = false;

        if (nom_text.length() == 0) {
            nom.setError("Merci de remplir le champ");
            error = true;
        }

        if (nombres_text.length() == 0) {
            nombres.setError("Merci de remplir le champ");
            error = true;
        }

        if (alerte_text.length() == 0) {
            alerte.setError("Merci de remplir le champ");
            error = true;
        }

        if (critique_text.length() == 0) {
            critique.setError("Merci de remplir le champ");
            error = true;
        }

        if (fournisseur.getSelectedItem().toString().length() == 0 || fournisseur.getSelectedItem().toString().equals("Aucun Fournisseur !")) {
            error = true;
            Toast.makeText(this, "Merci de sélectionner un fournisseur", Toast.LENGTH_LONG).show();
        }

        if (categorie.getSelectedItem().toString().length() == 0 || categorie.getSelectedItem().toString().equals("Acunne Categorie !")) {
            error = true;
            Toast.makeText(this, "Merci de sélectionner une categorie", Toast.LENGTH_LONG).show();
        }

        if (stockage.getSelectedItem().toString().length() == 0 || stockage.getSelectedItem().toString().equals("Aucun Stockage!")) {
            error = true;
            Toast.makeText(this, "Merci de sélectionner une stockage", Toast.LENGTH_LONG).show();
        }

        if (!error) {
            addItem();
        }

    }

    //Ajout dun item
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void addItem() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());

        HashMap<String, String> hashMapItem = new HashMap<>();

        hashMapItem.put("nom", nom_text.getText().toString());
        hashMapItem.put("quantite", nombres_text.getText().toString());
        hashMapItem.put("alerte", alerte_text.getText().toString());
        hashMapItem.put("critique", critique_text.getText().toString());
        hashMapItem.put("dateAjout", formattedDate);
        hashMapItem.put("stockage", String.valueOf(stockages.get((int) stockage.getSelectedItemId()).getId()));
        hashMapItem.put("categorie", String.valueOf(categories.get((int) categorie.getSelectedItemId()).getId()));
        hashMapItem.put("fournisseur", String.valueOf(fournisseurs.get((int) fournisseur.getSelectedItemId()).getId()));

        MainActivity.BDD.open();

        MainActivity.BDD.insert("item", hashMapItem);

        nom_text.setText("");
        nombres_text.setText("");
        alerte_text.setText("");
        critique_text.setText("");
        categorie.setSelection(0);
        fournisseur.setSelection(0);
        stockage.setSelection(0);

        Toast.makeText(MainActivity.Instance, R.string.app_tost_ajouter_item,
                Toast.LENGTH_LONG).show();
    }

    protected TextWatcher textWatcher() {
        return new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (nom.getEditText() == null || nombres.getEditText() == null || alerte.getEditText() == null || critique.getEditText() == null) {
                    return;
                }

                if (nom.getEditText().getText().hashCode() == s.hashCode() && count != 0) {
                    nom.setError(null);
                    return;
                }

                if (nombres.getEditText().getText().hashCode() == s.hashCode() && count != 0) {
                    nombres.setError(null);
                    return;
                }

                if (alerte.getEditText().getText().hashCode() == s.hashCode() && count != 0) {
                    alerte.setError(null);
                    return;
                }

                if (critique.getEditText().getText().hashCode() == s.hashCode() && count != 0) {
                    critique.setError(null);
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

    private void setSpiner(){
        //Open BDD
        MainActivity.BDD.open();

        //Get all fournisseur
        Cursor cursor = MainActivity.BDD.select("fournisseur");

        ArrayList<String> arraySpinnerFournisseur = new ArrayList<>();

        if (cursor != null) {

            while (cursor.moveToNext()) {

                //id INTEGER PRIMARY KEY AUTOINCREMENT, nom text, lieu INTEGER NOT NULL, email text, telephone int,

                String id = cursor.getString(0);
                String nom = cursor.getString(1);
                String lieu = cursor.getString(2);
                String email = cursor.getString(3);
                String telephone = cursor.getString(4);

                Fournisseur info = new Fournisseur(Integer.parseInt(id), nom, lieu, email, telephone);
                arraySpinnerFournisseur.add(info.getNom());
                fournisseurs.add(info);
            }

        }

        if (arraySpinnerFournisseur.isEmpty()) {
            arraySpinnerFournisseur.add("Aucun Fournisseur !");
        }

        //Ajout dans le spiner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinnerFournisseur);

        fournisseur.setAdapter(adapter);



        //Get all Categ
        cursor = MainActivity.BDD.select("categorie");

        ArrayList<String> arraySpinnerCateg = new ArrayList<>();

        if (cursor != null) {

            while (cursor.moveToNext()) {

                //id INTEGER PRIMARY KEY AUTOINCREMENT, nom text

                String id = cursor.getString(0);
                String nom = cursor.getString(1);

                Categorie info = new Categorie(Integer.parseInt(id), nom);
                arraySpinnerCateg.add(info.getNom());
                categories.add(info);
            }

        }

        if (arraySpinnerCateg.isEmpty()) {
            arraySpinnerCateg.add("Acunne Categorie !");
        }

        //Ajout dans le spiner
        ArrayAdapter<String> adapterCateg = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinnerCateg);

        categorie.setAdapter(adapterCateg);


        //Get all Stockage
        cursor = MainActivity.BDD.select("stockage");

        ArrayList<String> arraySpinnerStockage = new ArrayList<>();

        if (cursor != null) {

            while (cursor.moveToNext()) {

                //id INTEGER PRIMARY KEY AUTOINCREMENT, nom text, lieu INTEGER NOT NULL, dateAjout datetime

                String id = cursor.getString(0);
                String nom = cursor.getString(1);
                String lieu = cursor.getString(1);
                String dateAjout = cursor.getString(1);

                Stockage info = new Stockage(Integer.parseInt(id), nom,lieu,dateAjout);
                arraySpinnerStockage.add(info.getNom());
                stockages.add(info);
            }

        }

        if (arraySpinnerStockage.isEmpty()) {
            arraySpinnerStockage.add("Aucun Stockage!");
        }

        //Ajout dans le spiner
        ArrayAdapter<String> adapterStockage = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinnerStockage);

        stockage.setAdapter(adapterStockage);
    }

}
