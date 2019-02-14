package fr.debrisgrollaud.gestionsdesstock.item;

import android.app.ListActivity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Categorie;
import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Item;
import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Lieu;
import fr.debrisgrollaud.gestionsdesstock.BDD.relation.SeuilAlerte;
import fr.debrisgrollaud.gestionsdesstock.ListAdapter;
import fr.debrisgrollaud.gestionsdesstock.MainActivity;
import fr.debrisgrollaud.gestionsdesstock.R;
import fr.debrisgrollaud.gestionsdesstock.lieu.ModifierLieu;

//List les item
public class ListItem extends ListActivity {

    private ArrayList<HashMap<String, Object>> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HashMap<String, Object> item;
        List<String> listOption = new ArrayList<>();
        ArrayList<Integer> allert = null;

        list.clear();

        //Open BDD
        MainActivity.BDD.open();

        //Get all lieu
        Cursor cursor = MainActivity.BDD.select("item");


        if (cursor != null) {

            while (cursor.moveToNext()) {

                //id, nom, quantite, categorie, stockage, dateAjout, fournisseur, alerte, critique

                String id = cursor.getString(0);
                String nom = cursor.getString(1);
                String quantite = cursor.getString(2);
                String categorie = cursor.getString(3);
                String stockage = cursor.getString(4);
                String dateAjout = cursor.getString(5);
                String fournisseur = cursor.getString(6);
                String alerte = cursor.getString(7);
                String critique = cursor.getString(8);

                SeuilAlerte seuilAlerte = new SeuilAlerte(alerte, critique);
                Item objectinfo = new Item(Integer.parseInt(id), nom, quantite, categorie, stockage, dateAjout, fournisseur, seuilAlerte);

                item = null;
                item = new HashMap<>();
                item.put("nom", objectinfo.getNom());
                item.put("nombres", objectinfo.getQuantite());

                ArrayList<String> color = new ArrayList<>();
                color.add(alerte);
                color.add(critique);

                item.put("allert", color);
                item.put("instence", objectinfo);
                listOption.add((String) item.get("nom"));

                list.add(item);
                item = null;
            }

        }


        //envoie a l'adapteur
        try {
            ListAdapter adaptateur = new ListAdapter(this, list, listOption);
            setListAdapter(adaptateur);
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        if (list.isEmpty()) return;

//        HashMap<String, Object> info = list.get(position);
//
//        Lieu lieu = (Lieu) info.get("instence");
//
//        if (lieu == null) return;
//
//        HashMap<String, String> params = new HashMap<>();
//
//        params.put("id", String.valueOf(lieu.getId()));
//        params.put("numero", lieu.getNumero());
//        params.put("rue", lieu.getRue());
//        params.put("ville", lieu.getVille());
//        params.put("codepostal", lieu.getCodePostal());
//
//        MainActivity.setActivity(ModifierLieu.class, params);
    }
}
