package fr.debrisgrollaud.gestionsdesstock.lieu;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

        String[] values = new String[]{"Device", "Géo localisation"};
        Integer[] image = new Integer[]{R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground};

        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> item;
        List<String> listOption = new ArrayList<>();

        //item 1
        item = new HashMap<>();
        item.put("nom", "Nom Item 1");
        item.put("nombre", "5");
        listOption.add((String) item.get("nom"));

        list.add(item);

        //item 2
        item = new HashMap<>();
        item.put("nom", "Nom Item 2");
        item.put("nombre", "7");
        listOption.add((String) item.get("nom"));

        list.add(item);

        //item 3
        item = new HashMap<>();
        item.put("nom", "Nom Item 3");
        item.put("nombre", "1");
        listOption.add((String) item.get("nom"));

        list.add(item);

        ListAdapter adaptateur = new ListAdapter(this, list, listOption);
        setListAdapter(adaptateur);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this, "Position : " + position, Toast.LENGTH_LONG).show();
    }
}
