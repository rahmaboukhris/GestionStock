package fr.debrisgrollaud.gestionsdesstock.lieu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.debrisgrollaud.gestionsdesstock.R;

public class AjoutLieu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_lieu);
        setTitle(R.string.app_name_ajout_lieu);
    }
}
