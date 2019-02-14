package fr.debrisgrollaud.gestionsdesstock.fournisseur;

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

import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Fournisseur;
import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Stockage;
import fr.debrisgrollaud.gestionsdesstock.ListAdapter;
import fr.debrisgrollaud.gestionsdesstock.MainActivity;
import fr.debrisgrollaud.gestionsdesstock.R;
import fr.debrisgrollaud.gestionsdesstock.stockage.ModifierStockage;

//List les fournisseur
public class ListFournisseur extends ListActivity {

    ArrayList<HashMap<String, Object>> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HashMap<String, Object> item;
        List<String> listOption = new ArrayList<>();
        ArrayList<Integer> allert = null;

        list.clear();

        //Open BDD
        MainActivity.BDD.open();

        //Get all fournisseur
        Cursor cursor = MainActivity.BDD.select("fournisseur");

        if (cursor != null){

            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String nom = cursor.getString(1);
                String lieu = cursor.getString(2);
                String email = cursor.getString(3);
                String telephone = cursor.getString(4);

                Fournisseur fournisseur = new Fournisseur(Integer.parseInt(id),nom,lieu,email,telephone);

                item = null;
                item = new HashMap<>();
                item.put("nom",fournisseur.getNom());
                item.put("nombres","TODO"); //TODO : Get all usage
                item.put("allert", null);
                item.put("instence", fournisseur);
                listOption.add((String) item.get("nom"));

                list.add(item);
                item = null;
            }

        }

        //Send adapter
        try {
            ListAdapter adaptateur = new ListAdapter(this, list, listOption);
            setListAdapter(adaptateur);
        } catch (Exception e) {
            Log.e("ERROR",e.getMessage());
        }
    }

    @Override
    //Redirection vers la modif
    protected void onListItemClick(ListView l, View v, int position, long id) {
        if (list.isEmpty()) return;

        HashMap<String, Object> info = list.get(position);

        Fournisseur fournisseur = (Fournisseur) info.get("instence");

        if (fournisseur == null) return;

        HashMap<String, String> params = new HashMap<>();

        params.put("id", String.valueOf(fournisseur.getId()));
        params.put("nom", fournisseur.getNom());
        params.put("lieu", fournisseur.getLieu());
        params.put("email", fournisseur.getEmail());
        params.put("telephone", fournisseur.getTelephone());

        MainActivity.setActivity(ModifierFournisseur.class, params);
    }
}
