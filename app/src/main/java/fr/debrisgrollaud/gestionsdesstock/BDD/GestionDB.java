package fr.debrisgrollaud.gestionsdesstock.BDD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

//Fichier de gestion de la BDD
public class GestionDB {
    private SQLiteDatabase maBase;
    private BDD maBDD;

    //constructeur qui initialise la base
    public GestionDB(Context context) {
        maBDD = new BDD(context, "baseGestion", null, 20);
    }

    // on obtient l’accès en écriture, ce qui permet de lire et modifier les données
    public void open() {
        maBase = maBDD.getWritableDatabase();
    }

    //pour libérer la connection
    public void close() {
        maBase.close();
    }

    public boolean insert(String table, HashMap<String, String> map) {
        ContentValues contentValues = new ContentValues();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            contentValues.put(entry.getKey(), entry.getValue());
        }

        long result = maBase.insert(table, null, contentValues);
        return result != -1;
    }

    public boolean update(String Id, String table, HashMap<String, String> map) {
        ContentValues contentValues = new ContentValues();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            contentValues.put(entry.getKey(), entry.getValue());
        }

        long result = maBase.update(table, contentValues, "id=" + Id, null);
        return result != -1;
    }

    public Cursor select(String table) {
        Cursor res = maBase.rawQuery("select * from " + table, null);
        return res;
    }

    public Cursor select(String table, String where) {
        Cursor res = maBase.rawQuery("select * from " + table + " where " + where, null);
        return res;
    }
}
