package fr.debrisgrollaud.gestionsdesstock.lieu;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import fr.debrisgrollaud.gestionsdesstock.MainActivity;
import fr.debrisgrollaud.gestionsdesstock.R;

public class AjoutLieu extends AppCompatActivity {

    protected TextInputLayout ville;
    protected TextInputLayout rue;
    protected TextInputLayout num;
    protected TextInputLayout postal;
    protected EditText ville_text;
    protected EditText rue_text;
    protected EditText num_text;
    protected EditText postal_text;

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

        ville_text = ville.getEditText();
        rue_text = rue.getEditText();
        num_text = num.getEditText();
        postal_text = postal.getEditText();

        TextWatcher textWatcher = textWatcher();

        ville_text.addTextChangedListener(textWatcher);
        rue_text.addTextChangedListener(textWatcher);
        num_text.addTextChangedListener(textWatcher);
        postal_text.addTextChangedListener(textWatcher);

    }

    protected void onValide() {

        boolean error = false;

        if (ville_text.length() == 0) {
            ville.setError("Merci de remplir le champ");
            error = true;
        }

        if (rue_text.length() == 0) {
            rue.setError("Merci de remplir le champ");
            error = true;
        }

        if (num_text.length() == 0) {
            num.setError("Merci de remplir le champ");
            error = true;
        }

        if (postal_text.length() == 0) {
            postal.setError("Merci de remplir le champ");
            error = true;
        }

        if (postal_text.length() != 5) {
            postal.setError("Code Postal Invalide");
            error = true;
        }

        if (!error) {
            addLieu();
        }

    }

    protected void addLieu() {

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("ville", ville_text.getText().toString());
        hashMap.put("rue", rue_text.getText().toString());
        hashMap.put("numero", num_text.getText().toString());
        hashMap.put("codepost", postal_text.getText().toString());


        MainActivity.BDD.open();

        MainActivity.BDD.insert("lieu", hashMap);

        ville_text.setText("");
        rue_text.setText("");
        num_text.setText("");
        postal_text.setText("");

        Toast.makeText(MainActivity.Instance, R.string.app_tost_list_ajouter,
                Toast.LENGTH_LONG).show();
    }

    protected TextWatcher textWatcher() {
        return new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (ville.getEditText() == null || rue.getEditText() == null || num.getEditText() == null || postal.getEditText() == null) {
                    return;
                }

                if (ville.getEditText().getText().hashCode() == s.hashCode() && count != 0) {
                    ville.setError(null);
                    return;
                }

                if (rue.getEditText().getText().hashCode() == s.hashCode() && count != 0) {
                    rue.setError(null);
                    return;
                }

                if (num.getEditText().getText().hashCode() == s.hashCode() && count != 0) {
                    num.setError(null);
                    return;
                }

                if (postal.getEditText().getText().hashCode() == s.hashCode() && count != 0) {
                    postal.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

}
