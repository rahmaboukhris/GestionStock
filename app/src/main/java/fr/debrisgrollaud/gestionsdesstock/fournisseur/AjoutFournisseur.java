package fr.debrisgrollaud.gestionsdesstock.fournisseur;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import fr.debrisgrollaud.gestionsdesstock.R;

public class AjoutFournisseur extends AppCompatActivity {

    private TextInputLayout nom;
    private TextInputLayout lieu;
    private TextInputLayout email;
    private TextInputLayout telephone;
    private EditText nom_text;
    private EditText lieu_text;
    private EditText email_text;
    private EditText telephone_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_fournisseur);
    }
}
