package fr.debrisgrollaud.gestionsdesstock.lieu;

import android.app.ListActivity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import fr.debrisgrollaud.gestionsdesstock.BDD.relation.Lieu;
import fr.debrisgrollaud.gestionsdesstock.ListAdapter;
import fr.debrisgrollaud.gestionsdesstock.MainActivity;
import fr.debrisgrollaud.gestionsdesstock.R;

public class ListLieu extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name_list_lieu);

        ArrayList<HashMap<String, Object>> list = new ArrayList<>();

        HashMap<String, Object> item;
        List<String> listOption = new ArrayList<>();
        ArrayList<Integer> allert = null;

        list.clear();

        MainActivity.BDD.open();

        Cursor cursor = MainActivity.BDD.select("lieu");


        if (cursor != null){

            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String ville = cursor.getString(1);
                String rue = cursor.getString(2);
                String numero = cursor.getString(3);
                String postal = cursor.getString(4);

                Lieu lieu = new Lieu(Integer.parseInt(id),ville,rue,numero,postal);

                String adresse = lieu.getAdresse();

                item = null;
                item = new HashMap<>();
                item.put("nom",adresse);
                item.put("nombres",""); //TODO : Get all usage
                item.put("allert", null);
                listOption.add((String) item.get("adresse"));

                list.add(item);
                item = null;
            }

        }

        try {
            ListAdapter adaptateur = new ListAdapter(this, list, listOption);
            setListAdapter(adaptateur);
        } catch (Exception e) {
            Log.e("ERROR",e.getMessage());
        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this, "Position : " + position, Toast.LENGTH_LONG).show();
    }
}
