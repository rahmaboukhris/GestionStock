package fr.debrisgrollaud.gestionsdesstock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import fr.debrisgrollaud.gestionsdesstock.fournisseur.AjoutFournisseur;
import fr.debrisgrollaud.gestionsdesstock.fournisseur.ListFournisseur;
import fr.debrisgrollaud.gestionsdesstock.item.AjoutItem;
import fr.debrisgrollaud.gestionsdesstock.item.ListItem;
import fr.debrisgrollaud.gestionsdesstock.item.ModifierItem;
import fr.debrisgrollaud.gestionsdesstock.lieu.AjoutLieu;
import fr.debrisgrollaud.gestionsdesstock.lieu.ListLieu;
import fr.debrisgrollaud.gestionsdesstock.precommande.ListPrecommande;
import fr.debrisgrollaud.gestionsdesstock.stockage.AjoutStockage;
import fr.debrisgrollaud.gestionsdesstock.stockage.ListStockage;
import fr.debrisgrollaud.gestionsdesstock.stockage.ModifierStockage;

public class MainActivity extends AppCompatActivity {

    public static MainActivity Instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Instance = this;
        setContentView(R.layout.activity_main);
        setTitle(R.string.app_name);

        //Bouttons
        final Button button_toAjoutFournisseur = findViewById(R.id.toAjoutFournisseur);
        final Button button_toListFournisseur = findViewById(R.id.toListFournisseur);
        final Button button_toAjoutItem = findViewById(R.id.toAjoutItem);
        final Button button_toListItem = findViewById(R.id.toListItem);
        final Button button_toModifierItem = findViewById(R.id.toModifierItem);
        final Button button_toAjoutLieu = findViewById(R.id.toAjoutLieu);
        final Button button_toListLieu = findViewById(R.id.toListLieu);
        final Button button_toListPrecommande = findViewById(R.id.toListPrecommande);
        final Button button_toAjoutStockage = findViewById(R.id.toAjoutStockage);
        final Button button_toListStockage = findViewById(R.id.toListStockage);
        final Button button_toModifierStockage = findViewById(R.id.toModifierStockage);

        //Toutes les actions sont sur des clocks
        setActivityOnClick(button_toAjoutFournisseur, AjoutFournisseur.class);
        setActivityOnClick(button_toListFournisseur, ListFournisseur.class);
        setActivityOnClick(button_toAjoutItem, AjoutItem.class);
        setActivityOnClick(button_toListItem, ListItem.class);
        setActivityOnClick(button_toModifierItem, ModifierItem.class);
        setActivityOnClick(button_toAjoutLieu, AjoutLieu.class);
        setActivityOnClick(button_toListLieu, ListLieu.class);
        setActivityOnClick(button_toListPrecommande, ListPrecommande.class);
        setActivityOnClick(button_toAjoutStockage, AjoutStockage.class);
        setActivityOnClick(button_toListStockage, ListStockage.class);
        setActivityOnClick(button_toModifierStockage, ModifierStockage.class);

    }

    //Overload si pas de parma utilisés
    private static void setActivityOnClick(Button button, Class<?> caller) {
        setActivityOnClick(button, caller, null);
    }

    //définir action sur onclick
    public static void setActivityOnClick(Button button, Class<?> caller, HashMap<String, Object> params) {
        final Intent activity_toX = new Intent(MainActivity.Instance, caller);

        if (params != null) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                activity_toX.putExtra(param.getKey(), param.getKey());
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.Instance.startActivity(activity_toX);
            }
        });
    }
}
