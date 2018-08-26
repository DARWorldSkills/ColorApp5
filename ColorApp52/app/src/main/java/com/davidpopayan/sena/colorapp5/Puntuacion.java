package com.davidpopayan.sena.colorapp5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Puntuacion extends AppCompatActivity {
    //Declaración de variables
    TextView txtPrimero ,txtSegundo, txtTercero , txtCuarto, txtQuinto;

    //Método para la creacion de la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion);
        inizialite();
        inputData();
    }

    //Método para inicializar las vistas
    private void inizialite() {
        txtPrimero = findViewById(R.id.txtOro);
        txtSegundo = findViewById(R.id.txtPlata);
        txtTercero = findViewById(R.id.txtBronce);
        txtCuarto = findViewById(R.id.txtCuarto);
        txtQuinto = findViewById(R.id.txtQuinto);
    }

    //Método para el listado de puntuaciones
    private void inputData() {
        GestorDB gestorDB = new GestorDB(this);
        List<Score> scoreList = gestorDB.scoreList();
        if (scoreList.size()>0){
            txtPrimero.setText(Integer.toString(scoreList.get(0).getPuntaje()));
        }else {
            Toast.makeText(this, "No hay puntuaciones disponibles", Toast.LENGTH_SHORT).show();
        }

        if (scoreList.size()>1){
            txtSegundo.setText(Integer.toString(scoreList.get(1).getPuntaje()));
        }


        if (scoreList.size()>2){
            txtTercero.setText(Integer.toString(scoreList.get(2).getPuntaje()));
        }

        if (scoreList.size()>3){
            txtCuarto.setText(Integer.toString(scoreList.get(3).getPuntaje()));
        }

        if (scoreList.size()>4){
            txtQuinto.setText(Integer.toString(scoreList.get(4).getPuntaje()));
        }

    }
}
