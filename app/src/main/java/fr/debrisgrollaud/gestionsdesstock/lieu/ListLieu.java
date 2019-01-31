package fr.debrisgrollaud.gestionsdesstock.lieu;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import fr.debrisgrollaud.gestionsdesstock.ListAdapter;
import fr.debrisgrollaud.gestionsdesstock.R;

public class ListLieu extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name_list_lieu);

        String[] values = new String[]{"Device", "Géo localisation"};
        Integer[] image = new Integer[]{R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground};

        ArrayList<HashMap<String, Object>> list = new ArrayList<>();

        //BOUCHON START
        //TODO : Récuperer de la BDD

        HashMap<String, Object> item;
        List<String> listOption = new ArrayList<>();
        ArrayList<Integer> allert;


        //item 1
        allert = new ArrayList<>();
        allert.add(5);
        allert.add(2);

        item = new HashMap<>();
        item.put("nom", "Nom Item 1");
        item.put("nombre", "5");
        item.put("allert", allert);
        listOption.add((String) item.get("nom"));

        list.add(item);


        //item 2
        allert = new ArrayList<>();
        allert.add(5);
        allert.add(2);

        item = new HashMap<>();
        item.put("nom", "Nom Item 2");
        item.put("nombre", "7");
        item.put("allert", allert);
        listOption.add((String) item.get("nom"));

        list.add(item);


        //item 3
        allert = new ArrayList<>();
        allert.add(5);
        allert.add(2);

        item = new HashMap<>();
        item.put("nom", "Nom Item 3");
        item.put("nombre", "1");
        item.put("allert", allert);
        listOption.add((String) item.get("nom"));

        list.add(item);

        //BOUCHON END

        ListAdapter adaptateur = new ListAdapter(this, list, listOption);
        setListAdapter(adaptateur);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this, "Position : " + position, Toast.LENGTH_LONG).show();
    }
}
