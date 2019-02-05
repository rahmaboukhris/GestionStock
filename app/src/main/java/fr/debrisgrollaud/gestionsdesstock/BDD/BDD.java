package fr.debrisgrollaud.gestionsdesstock.BDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDD extends SQLiteOpenHelper {

    //constructeur
    public BDD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //création des tables dans la base de données
    @Override
    public void onCreate(SQLiteDatabase db) {
        String req = "create table stockage(id int, nom text, lieu text, dateAjout datetime)";
        db.execSQL(req);
        req = "create table fournisseur(id int, nom text, lieu text, email text, telephone int)";
        db.execSQL(req);
        req = "create table lieux(id int, ville text, rue text, numero int, codepost text)";
        db.execSQL(req);
        req = "create table seuil(alerte text, critique text)";
        db.execSQL(req);
        req = "create table item(id int, nom text, quantite int, reference text, categorie text, dateAjout datetime, fournisseur text)";
        db.execSQL(req);
        req = "create table categorie(id int, nom text)";
        db.execSQL(req);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // code de modification éventuelle de la structure de la base de données
    }
}
