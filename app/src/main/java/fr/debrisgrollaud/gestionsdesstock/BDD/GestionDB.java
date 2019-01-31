package fr.debrisgrollaud.gestionsdesstock.BDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class GestionDB {
    private SQLiteDatabase maBase;
    private BDD maBDD;

    //constructeur qui initialise la base

    public GestionDB(Context context) {
        maBDD = new BDD(context, "baseGestion", null, 1);
    }

    public void open() {
        maBase = maBDD.getWritableDatabase();
    }
    // on obtient l’accès en écriture, ce qui permet de lire et modifier les données

    public void close() {
        maBase.close();
    }
    //pour libérer la connection
}
