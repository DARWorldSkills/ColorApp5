package com.davidpopayan.sena.colorapp5;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Juego extends AppCompatActivity implements View.OnClickListener {
    //Declaración de variables
    List<String> listPalabras = new ArrayList<>();
    List<Integer> listaColores = new ArrayList<>();
    List<Integer> listaColoresTmp = new ArrayList<>();
    public static int correctas, incorrectas;
    int faltantes, nPalabras;
    ImageButton btnColor1, btnColor2, btnColor3, btnColor4;
    TextView txtNPalabras,txtCorrectas, txtFaltantes, txtTiempo, txtPalabra;
    ProgressBar pbTiempoo;
    FloatingActionButton btnPause;
    boolean bandera;
    boolean bandera1;
    int ab=0;
    int [] segundos ={0,30};
    int icR, ipR;
    int valorcito, nPausas;

    //Método para la creación de la actividad Juego
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        inizialite();
        inputLists();
        inputOnClickListener();
        inputValues();
        inputData();
        randomizar();
        goGame();
    }




    //Método para inizializar las vistas
    private void inizialite() {
        txtCorrectas = findViewById(R.id.txtCorrectas);
        txtNPalabras = findViewById(R.id.txtNPalabras);
        txtFaltantes = findViewById(R.id.txtFaltantes);
        txtTiempo = findViewById(R.id.txtTiempo);
        txtPalabra = findViewById(R.id.txtPalabra);
        pbTiempoo = findViewById(R.id.pbTiempo);
        btnColor1 = findViewById(R.id.btnColor1);
        btnColor2 = findViewById(R.id.btnColor2);
        btnColor3 = findViewById(R.id.btnColor3);
        btnColor4 = findViewById(R.id.btnColor4);
        btnPause = findViewById(R.id.btnPause);
    }

    //Método para ingresar a las listas listPalabra y listColores
    private void inputLists() {
        listPalabras = new ArrayList<>();
        listaColores = new ArrayList<>();
        listPalabras.add("AMARILLO");
        listPalabras.add("AZUL");
        listPalabras.add("ROJO");
        listPalabras.add("VERDE");
        listaColores.add(getColor(R.color.colorAmarillo));
        listaColores.add(getColor(R.color.colorAzul));
        listaColores.add(getColor(R.color.colorVerde));
        listaColores.add(getColor(R.color.colorRojo));
    }


    //Método para ingresar el setOnClickListener a los botones
    private void inputOnClickListener() {
        btnColor1.setOnClickListener(this);
        btnColor2.setOnClickListener(this);
        btnColor3.setOnClickListener(this);
        btnColor4.setOnClickListener(this);
        btnPause.setOnClickListener(this);
    }

    //Método para ingresar valores al juego
    private void inputValues() {
        correctas = 0;
        incorrectas=0;
        bandera=true;
        bandera1=true;
        ab=0;
        faltantes=3;
        nPausas=0;

    }

    private void inputData() {
        txtCorrectas.setText(Integer.toString(correctas));
        txtFaltantes.setText(Integer.toString(faltantes));
        txtNPalabras.setText(Integer.toString(nPalabras));
    }

    //Método para hacer aleatorio los botones, color de palabra y palabra
    private void randomizar() {
        listaColoresTmp = listaColores;
        Collections.shuffle(listaColoresTmp);
        icR = (int) (Math.random()*4);
        ipR = (int) (Math.random()*4);
        txtPalabra.setText(listPalabras.get(ipR));
        txtPalabra.setTextColor(listaColores.get(icR));

        btnColor1.setColorFilter(listaColoresTmp.get(0));
        btnColor2.setColorFilter(listaColoresTmp.get(1));
        btnColor3.setColorFilter(listaColoresTmp.get(2));
        btnColor4.setColorFilter(listaColoresTmp.get(3));


    }

    //Método para correr el tiempo del juego
    private void goGame() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (bandera){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (bandera1) {
                                segundos[0]++;
                                segundos[1]--;
                                txtTiempo.setText("Tiempo: " + segundos[1]);
                                if (segundos[0] >= 3) {
                                    segundos[0] = 0;
                                    randomizar();
                                    inputData();
                                    incorrectas++;
                                    faltantes--;
                                }
                                endGame();
                            }
                        }
                    });
                }
            }
        });
        thread.start();
    }

    //Método para finalizar el juego
    private void endGame() {
        if (ab==0 && (faltantes==0 || segundos[1]>=30)){
            ab=1;
            bandera1=false;
            bandera=false;
        }
    }

    //Método para escuchar cuando se hace click
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnColor1:
                valorcito=1;
                validar();
                break;

            case R.id.btnColor2:
                valorcito=2;
                validar();
                break;


            case R.id.btnColor3:
                valorcito=3;
                validar();
                break;

            case R.id.btnColor4:
                valorcito=4;
                validar();
                break;

            case R.id.btnPause:
                pauseGame();
                break;

        }
    }

    //Método para pausar el juego
    private void pauseGame() {
        nPausas++;
        if (nPausas<=2){
            bandera1=false;
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.item_pause);
            dialog.setCancelable(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            ImageButton btnContinuar = dialog.findViewById(R.id.btnContinuar);
            btnContinuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    segundos[0]=0;
                    bandera1=true;
                    randomizar();
                    dialog.cancel();

                }
            });
            dialog.show();
        }else {
            Toast.makeText(this, "Ha alcanzado el limite de pausas (solo puede tener dos)", Toast.LENGTH_SHORT).show();
        }
    }

    //Método para validar el intento del jugador
    private void validar() {
        nPalabras++;
        if (valorcito==1){
            if (icR==0){
                correctas++;
            }else {
                faltantes--;
                incorrectas--;
            }
        }


        if (valorcito==2){
            if (icR==1){
                correctas++;
            }else {
                faltantes--;
                incorrectas--;
            }
        }


        if (valorcito==3){
            if (icR==2){
                correctas++;
            }else {
                faltantes--;
                incorrectas--;
            }
        }


        if (valorcito==4){
            if (icR==3){
                correctas++;
            }else {
                faltantes--;
                incorrectas--;
            }
        }

        segundos[0]=0;
        inputData();
        randomizar();
        endGame();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bandera1=false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        bandera1=false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera1=false;
        bandera=false;
    }
}
