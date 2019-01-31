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

    //Liste of item
    private static ArrayList<String> nom = new ArrayList<>();
    private static ArrayList<String> nombre = new ArrayList<>();
    private static ArrayList<ArrayList<Integer>> allert = new ArrayList<>();


    //Call on create view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.adapter_list, parent, false);

        if (nombre.size() != 0) {
            //Defined Text
            TextView text_nom = (TextView) rowView.findViewById(R.id.text_adapter_nom);
            TextView text_nombre = (TextView) rowView.findViewById(R.id.text_adapter_nombre);

            text_nombre.setText(nombre.get(position));
            text_nom.setText(nom.get(position));

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
        } else {
            TextView text_nom = (TextView) rowView.findViewById(R.id.text_adapter_nom);
            TextView text_nombre = (TextView) rowView.findViewById(R.id.text_adapter_nombre);

            text_nom.setText("Erreur No Item");
            text_nombre.setText("Position : " + position);
        }


        if (convertView != null) rowView = (View) convertView;

        return rowView;
    }

    //Constructor non argument
    public ListAdapter(Context context, String[] values) {
        super(context, R.layout.adapter_list, values);
    }

    //Constructor List argument
    public ListAdapter(Context context, ArrayList<HashMap<String, Object>> list, List<String> listOption) {
        super(context, R.layout.adapter_list, listOption);

        //Create list of item
        for (HashMap<String, Object> item : list) {
            nom.add((String) item.get("nom"));
            nombre.add((String) item.get("nombre"));
            allert.add((ArrayList<Integer>) item.get("allert"));
        }


    }


}
