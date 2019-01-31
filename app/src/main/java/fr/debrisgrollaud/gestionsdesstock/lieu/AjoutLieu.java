package fr.debrisgrollaud.gestionsdesstock.lieu;

import android.Manifest;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.debrisgrollaud.gestionsdesstock.MainActivity;
import fr.debrisgrollaud.gestionsdesstock.R;

public class AjoutLieu extends AppCompatActivity {

    private TextInputLayout ville;
    private TextInputLayout rue;
    private TextInputLayout num;
    private TextInputLayout postal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_lieu);
        setTitle(R.string.app_name_ajout_lieu);

        Button valider = findViewById(R.id.button_ajout_lieux);

        ville = findViewById(R.id.input_ajoutLieu_ville);
        rue = findViewById(R.id.input_ajoutLieu_rue);
        num = findViewById(R.id.input_ajoutLieu_nuemro);
        postal = findViewById(R.id.input_ajoutLieu_postal);

        valider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onValide();
            }
        });


    }

    private void onValide() {

        EditText ville_text = ville.getEditText();
        EditText rue_text = rue.getEditText();
        EditText num_text = num.getEditText();
        EditText postal_text = postal.getEditText();

        if (ville_text.length() == 0){
            ville.setError("Merci de remplir le champ");
        }

        if (rue_text.length() == 0){
            rue.setError("Merci de remplir le champ");
        }

        if (num_text.length() == 0){
            num.setError("Merci de remplir le champ");
        }

        if (postal_text.length() == 0){
            postal.setError("Merci de remplir le champ");
        }

        if (postal_text.length() != 5) {
            postal.setError("Code Postal Invalide");
        }
    }
}
