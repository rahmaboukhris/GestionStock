package fr.debrisgrollaud.gestionsdesstock.precommande;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.debrisgrollaud.gestionsdesstock.ListAdapter;
import fr.debrisgrollaud.gestionsdesstock.R;

//List les precommande en fonction du seuil d'alerte
public class ListPrecommande extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> list = new ArrayList<>();

        //TODO : Get BDD
        list.add("Null");

        String array[] = new String[list.size()];
        for(int j =0;j<list.size();j++){
            array[j] = list.get(j);
        }

        ListAdapter adaptateur = new ListAdapter(this, array);
        setListAdapter(adaptateur);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this, "Position : " + position, Toast.LENGTH_LONG).show();
    }
}
