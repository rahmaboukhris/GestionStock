package fr.debrisgrollaud.gestionsdesstock.stockage;

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

public class AjoutStockage extends AppCompatActivity {

    private TextInputLayout numero;
    private TextInputLayout stockage;
    private EditText numero_text;
    private EditText stockage_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_stockage);
        setTitle("Ajout d'un stockage");

        Button valider = findViewById(R.id.button_ajout_stockage);

        numero = findViewById(R.id.input_ajoutStockage_numero);
        stockage = findViewById(R.id.input_ajoutStockage);

        valider.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onValide();
            }
        });

        numero_text = numero.getEditText();
        stockage_text = stockage.getEditText();

        TextWatcher textWatcher = textWatcher();

        numero_text.addTextChangedListener(textWatcher);
        stockage_text.addTextChangedListener(textWatcher);
    }

    private void onValide() {

        boolean error = false;

        if (numero_text.length() == 0) {
            numero.setError("Merci de remplir le champ");
            error = true;
        }

        if (stockage_text.length() == 0) {
            stockage.setError("Merci de remplir le champ");
            error = true;
        }

        if (!error) {
            addStockage();
        }

    }

    private void addStockage() {

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("ville", numero_text.getText().toString());
        hashMap.put("rue", stockage_text.getText().toString());


        MainActivity.BDD.open();

        MainActivity.BDD.insert("stockage", hashMap);

        numero_text.setText("");
        stockage_text.setText("");

        Toast.makeText(MainActivity.Instance, R.string.app_tost_list_ajouter,
                Toast.LENGTH_LONG).show();
    }

    private TextWatcher textWatcher() {
        return new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (numero.getEditText() == null || stockage.getEditText() == null) {
                    return;
                }

                if (numero.getEditText().getText().hashCode() == s.hashCode() && count != 0) {
                    numero.setError(null);
                    return;
                }

                if (stockage.getEditText().getText().hashCode() == s.hashCode() && count != 0) {
                    stockage.setError(null);
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }
}
