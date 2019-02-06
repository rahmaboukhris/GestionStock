package fr.debrisgrollaud.gestionsdesstock;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListAdapter extends ArrayAdapter<String> {

    //Liste des item
    private ArrayList<String> nom = new ArrayList<>();
    private ArrayList<String> nombre = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> allert = new ArrayList<>();


    //appel du create view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.adapter_list, parent, false);

        if (nombre.size() != 0) {
            //définition du texte
            TextView text_nom = (TextView) rowView.findViewById(R.id.text_adapter_nom);
            TextView text_nombre = (TextView) rowView.findViewById(R.id.text_adapter_nombre);

            text_nombre.setText(nombre.get(position));
            text_nom.setText(nom.get(position));

            if (allert.get(position) != null) {

                //Get Level warning and danger
                int warning = allert.get(position).get(0);
                int danger = allert.get(position).get(1);

                //Set Background color
                if (Integer.parseInt(nombre.get(position)) <= warning) {
                    rowView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorWarning));
                }

                if (Integer.parseInt(nombre.get(position)) <= danger) {
                    rowView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDanger));
                }

            }

        } else {
            TextView text_nom = (TextView) rowView.findViewById(R.id.text_adapter_nom);
            TextView text_nombre = (TextView) rowView.findViewById(R.id.text_adapter_nombre);

            text_nom.setText("Erreur No Item");
            text_nombre.setText("Position : " + position);
        }


        if (convertView != null) rowView = (View) convertView;

        return rowView;
    }

    //constructeur sans argument
    public ListAdapter(Context context, String[] values) {
        super(context, R.layout.adapter_list, values);
    }

    //constructeur avec liste des arguments
    public ListAdapter(Context context, ArrayList<HashMap<String, Object>> list, List<String> listOption) throws Exception {
        super(context, R.layout.adapter_list, listOption);

        if (list.size() != listOption.size()){
            throw new Exception("Erreur dans la taille de la liste !");
        }

        //création de la liste des items
        for (HashMap<String, Object> item : list) {
            nom.add((String) item.get("nom"));
            nombre.add((String) item.get("nombre"));
            allert.add((ArrayList<Integer>) item.get("allert"));
        }


    }
}
