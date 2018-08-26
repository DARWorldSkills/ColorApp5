package com.davidpopayan.sena.colorapp5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class GestorDB extends SQLiteOpenHelper{
    //Constructor para la creación de base de datos
    public GestorDB(Context context) {
        super(context, "colorApp.db",null,1);
    }

    //Método para cuando se crea la base de datos hacer un tipo de configuración o creación de tablas
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SCORE (PUNTAJE INTEGER, INCORRECTAS INTEGER)");
    }

    //Método para ingresar valores a la tabla SCORE
    public void inputData(Score score){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PUNTAJE",score.getPuntaje());
        values.put("INCORRECTAS",score.getIncorrectas());
        db.insert("SCORE",null,values);
        db.close();
    }

    //Método para listar los valores de la tabla SCORE
    public List<Score> scoreList(){
        List<Score> results = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SCORE ORDER BY PUNTAJE DESC, INCORRECTAS DESC;",null);
        if (cursor.moveToFirst()){
            do {
                Score score = new Score();
                score.setPuntaje(cursor.getInt(0));
                score.setIncorrectas(cursor.getInt(1));
                results.add(score);

            }while (cursor.moveToNext());
        }
        return results;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
