package fr.debrisgrollaud.gestionsdesstock.fournisseur;

import android.database.Cursor;
import android.support.design.widget.TextInputEditText;
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

import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Lieu;
import fr.debrisgrollaud.gestionsdesstock.MainActivity;
import fr.debrisgrollaud.gestionsdesstock.R;

//Ajout d'un fournisseur
public class AjoutFournisseur extends AppCompatActivity {

    //Utils
    protected TextInputLayout nom;
    protected Spinner lieu;
    protected TextInputLayout email;
    protected TextInputLayout telephone;

    protected EditText nom_text;
    protected EditText email_text;
    protected EditText telephone_text;

    protected ArrayList<Lieu> lieux = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_fournisseur);

        //Recup des element
        Button valider = findViewById(R.id.button_ajout_fournisseur);

         Button valider = findViewById(R.id.button_ajout_fournisseur);

        nom = findViewById(R.id.input_ajoutFournisseur_nom);
        email = findViewById(R.id.input_ajoutFournisseur_e_mail);
        telephone = findViewById(R.id.input_ajoutFournisseur_telephone);
        lieu = findViewById(R.id.spiner_ajoutFournisseur_lieu);

        // Action on clique
        valider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onValide();
            }
        });

        //Get text
        nom_text = nom.getEditText();
        email_text = email.getEditText();
        telephone_text = telephone.getEditText();

        //Text Watcher, on validate
        TextWatcher textWatcher = textWatcher();

        nom_text.addTextChangedListener(textWatcher);
        email_text.addTextChangedListener(textWatcher);
        telephone_text.addTextChangedListener(textWatcher);

        //Open BDD
        MainActivity.BDD.open();

        //Get all lieu
        Cursor cursor = MainActivity.BDD.select("lieu");

        ArrayList<String> arraySpinner = new ArrayList<>();

        if (cursor != null) {

            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String ville = cursor.getString(1);
                String rue = cursor.getString(2);
                String numero = cursor.getString(3);
                String postal = cursor.getString(4);

                Lieu lieu = new Lieu(Integer.parseInt(id), numero, rue, ville, postal);
                String adresse = lieu.toString();
                arraySpinner.add(adresse);
                lieux.add(lieu);
            }

        }

        if (arraySpinner.isEmpty()) {
            arraySpinner.add("Acunne Adresse !");
        }

        //Ajout dans le spiner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);

        lieu.setAdapter(adapter);

    }

    //on validate on clique
    protected void onValide() {

        boolean error = false;

        if (nom_text.length() == 0) {
            nom.setError("Merci de remplir le champ");
            error = true;
        }

        if (email_text.length() == 0) {
            email/0;
            email.setError("Merci de remplir le champ");
            error = true;
        }

        if (telephone_text.length() == 0) {
            telephone.setError("Merci de remplir le champ");
            error = true;
        }

        if (lieu.getSelectedItem().toString().length() == 0 || lieu.getSelectedItem().toString().equals("Acunne Adresse !")) {
            error = true;
            Toast.makeText(this, "Merci de sélectionner un lieu", Toast.LENGTH_LONG).show();
        }

        if (!error) {
            addFournisseur();
        }

    }

    //Ajout du fournissuer
    protected void addFournisseur() {

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("nom", nom_text.getText().toString());
        hashMap.put("email", email_text.getText().toString());
        hashMap.put("telephone", telephone_text.getText().toString());
        hashMap.put("lieu", String.valueOf(lieux.get((int) lieu.getSelectedItemId()).getId()));


        MainActivity.BDD.open();

        MainActivity.BDD.insert("fournisseur", hashMap);

        nom_text.setText("");
        email_text.setText("");
        telephone_text.setText("");
        lieu.setSelection(0);

        Toast.makeText(MainActivity.Instance, R.string.app_tost_ajouter_forunisseur,
                Toast.LENGTH_LONG).show();
    }

    protected TextWatcher textWatcher() {
        return new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (nom.getEditText() == null || email.getEditText() == null || telephone.getEditText() == null) {
                    return;
                }

                if (nom.getEditText().getText().hashCode() == s.hashCode() && count != 0) {
                    nom.setError(null);
                    return;
                }

                if (email.getEditText().getText().hashCode() == s.hashCode() && count != 0) {
                    email.setError(null);
                    return;
                }

                if (telephone.getEditText().getText().hashCode() == s.hashCode() && count != 0) {
                    telephone.setError(null);
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

}
