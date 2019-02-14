package fr.debrisgrollaud.gestionsdesstock.categorie;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Categorie;
import fr.debrisgrollaud.gestionsdesstock.ListAdapter;
import fr.debrisgrollaud.gestionsdesstock.MainActivity;
import fr.debrisgrollaud.gestionsdesstock.R;

public class ListCategorie extends ListActivity {
    private ArrayList<HashMap<String, Object>> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Liste des catégories");

        HashMap<String, Object> item;
        List<String> listOption = new ArrayList<>();
        ArrayList<Integer> allert = null;

        list.clear();

        MainActivity.BDD.open();

        Cursor cursor = MainActivity.BDD.select("categorie");


        if (cursor != null) {

            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String nom = cursor.getString(1);

                Categorie categorie = new Categorie(Integer.parseInt(id), nom);

                item = null;
                item = new HashMap<>();
                item.put("nom", categorie.getNom());
                listOption.add((String) item.get("nom"));

                list.add(item);
                item = null;
            }

        }

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

        HashMap<String, Object> info = list.get(position);

        Categorie categorie = (Categorie) info.get("instence");

        if (categorie == null) return;

        HashMap<String, String> params = new HashMap<>();

        params.put("id", String.valueOf(categorie.getId()));
        params.put("nom", categorie.getNom());

        MainActivity.setActivity(ModifierCategorie.class, params);
    }
}
