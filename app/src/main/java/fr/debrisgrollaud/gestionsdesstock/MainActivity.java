package fr.debrisgrollaud.gestionsdesstock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static Intent toAjoutLieuActivity;
    private static Intent toListLieuActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        Button toAjoutLieuButton = findViewById(R.id.toAjoutFournisseur);

        toAjoutLieuActivity = new Intent(MainActivity.this, AjoutLieu.class);

        toAjoutLieuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.startActivity(toAjoutLieuActivity);
            }
        });

        final Button toListLieuButton = findViewById(R.id.toListLieu);

        toListLieuActivity = new Intent(MainActivity.this, ListLieu.class);

        toListLieuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.startActivity(toListLieuActivity);
            }
        });


        //myIntent.putExtra("key", value); //Optional parameters

    }
}
