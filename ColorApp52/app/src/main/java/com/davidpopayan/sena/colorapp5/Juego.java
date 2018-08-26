package com.davidpopayan.sena.colorapp5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Juego extends AppCompatActivity {
    List<String> listPalabras = new ArrayList<>();
    List<Integer> listaColores = new ArrayList<>();
    List<Integer> listaColoresTmp = new ArrayList<>();
    public static int correctas, incorrectas;
    int faltantes, nPalabras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
    }
}
