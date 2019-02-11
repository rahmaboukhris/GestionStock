package fr.debrisgrollaud.gestionsdesstock.lieu;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Lieu;
import fr.debrisgrollaud.gestionsdesstock.MainActivity;
import fr.debrisgrollaud.gestionsdesstock.R;

//Modif un lieu
public class ModifierLieu extends AjoutLieu {

    private String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name_modifier_lieu);
        Lieu lieu;

        Bundle extras = getIntent().getExtras();
        if (extras == null) return;

        Id = extras.getString("id");

        lieu = new Lieu(extras.getString("numero"), extras.getString("rue"), extras.getString("ville"), extras.getString("codepostal"));

        if (lieu.toString().length() <= 0) {
            MainActivity.setActivity(ListLieu.class);
            return;
        }

        ville_text.setText(lieu.getVille());
        rue_text.setText(lieu.getRue());
        num_text.setText(lieu.getNumero());
        postal_text.setText(lieu.getCodePostal());
    }

    @Override
    protected void addLieu() {

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put("ville", ville_text.getText().toString());
        hashMap.put("rue", rue_text.getText().toString());
        hashMap.put("numero", num_text.getText().toString());
        hashMap.put("codepost", postal_text.getText().toString());

        MainActivity.BDD.open();

        MainActivity.BDD.update(Id,"lieu", hashMap);

        MainActivity.setActivity(ListLieu.class);
    }

}
