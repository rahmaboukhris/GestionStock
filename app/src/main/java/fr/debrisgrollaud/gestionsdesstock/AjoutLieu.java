package fr.debrisgrollaud.gestionsdesstock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AjoutLieu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_lieu);
        setTitle(R.string.app_name_ajout_lieu);
    }
}
