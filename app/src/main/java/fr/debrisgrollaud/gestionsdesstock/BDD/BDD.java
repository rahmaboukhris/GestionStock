package fr.debrisgrollaud.gestionsdesstock.BDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDD extends SQLiteOpenHelper {

    //constructeur
    public BDD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String req = "create table stockage(id text, nom text)";
        db.execSQL(req);
        req = "create table fournisseur(id text, nom text)";
        db.execSQL(req);
        req = "create table lieux(id text, ville text, rue text, numero text, codepost text)";
        db.execSQL(req);
        req = "create table seuil(alerte text, critique text)";
        db.execSQL(req);
        req = "create table item(id text, nom text, quantier text, reference text)";
        db.execSQL(req);
        req = "create table categorie(id text, nom text)";
        db.execSQL(req);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // code de modification éventuelle de la structure de la base de données
    }
}
