package fr.debrisgrollaud.gestionsdesstock;

import android.content.Context;
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

    private static ArrayList<String> nom = new ArrayList<>();
    private static ArrayList<String> nombre = new ArrayList<>();


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.adapter_list, parent, false);

        TextView text_nom = (TextView) rowView.findViewById(R.id.text_adapter_nom);
        TextView text_nombre = (TextView) rowView.findViewById(R.id.text_adapter_nombre);

        text_nombre.setText(nombre.get(position));
        text_nom.setText(nom.get(position));

        if (convertView != null) rowView = (View) convertView;

        return rowView;
    }

    public ListAdapter(Context context, String[] values) {
        super(context, R.layout.adapter_list, values);
    }

    public ListAdapter(Context context, ArrayList<HashMap<String, Object>> list, List<String> listOption) {
        super(context, R.layout.adapter_list, listOption);

        for (HashMap<String, Object> item : list) {
            nom.add((String) item.get("nom"));
            nombre.add((String) item.get("nombre"));
        }


    }


}
