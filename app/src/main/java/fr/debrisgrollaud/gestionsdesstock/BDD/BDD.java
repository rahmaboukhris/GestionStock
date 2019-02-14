package fr.debrisgrollaud.gestionsdesstock.BDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Fichier de creation de la BDD
public class BDD extends SQLiteOpenHelper {

    private String req;

    //constructeur
    public BDD(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //création des tables dans la base de données
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Ajout des diferente tables
        req = "create table lieu(id INTEGER PRIMARY KEY AUTOINCREMENT, ville text, rue text, numero int, codepost text)";
        db.execSQL(req);

        req = "create table stockage(id INTEGER PRIMARY KEY AUTOINCREMENT, nom text, lieu INTEGER NOT NULL, dateAjout datetime, " +
                "FOREIGN KEY(lieu) REFERENCES lieu(id))";
        db.execSQL(req);

        req = "create table fournisseur(id INTEGER PRIMARY KEY AUTOINCREMENT, nom text, lieu INTEGER NOT NULL, email text, telephone int," +
                "FOREIGN KEY(lieu) REFERENCES lieu(id))";
        db.execSQL(req);

        req = "create table categorie(id INTEGER PRIMARY KEY AUTOINCREMENT, nom text)";
        db.execSQL(req);

        req = "create table item(id INTEGER PRIMARY KEY AUTOINCREMENT, nom text, quantite text, reference text, categorie INTEGER, dateAjout datetime, fournisseur INTEGER," +
                "FOREIGN KEY(categorie) REFERENCES categorie(id),FOREIGN KEY(fournisseur) REFERENCES fournisseur(id))";
        db.execSQL(req);

        req = "create table seuil(id_stokage INTEGER,id_item INTEGER, alerte text, critique text," +
                "PRIMARY KEY (id_stokage, id_item))";
        db.execSQL(req);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Suppression des tables dans l'ordre inverse
        req = "DROP TABLE seuil";
        db.execSQL(req);

        req = "DROP TABLE item";
        db.execSQL(req);

        req = "DROP TABLE categorie";
        db.execSQL(req);

        req = "DROP TABLE fournisseur";
        db.execSQL(req);

        req = "DROP TABLE stockage";
        db.execSQL(req);

        req = "DROP TABLE lieu";
        db.execSQL(req);

        this.onCreate(db);
    }
}
