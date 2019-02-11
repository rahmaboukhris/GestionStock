package fr.debrisgrollaud.gestionsdesstock.item;

import android.os.Bundle;

import fr.debrisgrollaud.gestionsdesstock.R;

//Modif d'un item
public class ModifierItem extends AjoutItem {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_item);
    }
}
