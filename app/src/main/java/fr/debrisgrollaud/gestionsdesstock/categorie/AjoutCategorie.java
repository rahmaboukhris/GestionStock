package fr.debrisgrollaud.gestionsdesstock.categorie;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import fr.debrisgrollaud.gestionsdesstock.MainActivity;
import fr.debrisgrollaud.gestionsdesstock.R;

public class AjoutCategorie extends AppCompatActivity {

    protected TextInputLayout nom;
    protected EditText nom_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_categorie);
        setTitle("Ajout d'une catégorie");

        Button valider = findViewById(R.id.button_ajout_categorie);

        nom = findViewById(R.id.input_ajoutCategorie_nom);

        valider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onValide();
            }
        });

        nom_text = nom.getEditText();

        TextWatcher textWatcher = textWatcher();

        nom_text.addTextChangedListener(textWatcher);

    }

    protected void onValide() {

        boolean error = false;

        if (nom_text.length() == 0) {
            nom.setError("Merci de remplir le champ");
            error = true;
        }

        if (!error) {
            addCategorie();
        }

    }

    protected void addCategorie() {

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("nom", nom_text.getText().toString());


        MainActivity.BDD.open();

        MainActivity.BDD.insert("categorie", hashMap);

        nom_text.setText("");

        Toast.makeText(MainActivity.Instance, R.string.app_tost_ajouter_categorie,
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