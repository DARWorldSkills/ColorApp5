package com.davidpopayan.sena.colorapp5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuT extends AppCompatActivity implements View.OnClickListener{
    //Declaración de variables
    Button btnJugar, btnPuntuacion, btnConfiguracion;
    public static int guardar = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_t);
        inizialite();
        inputClickListener();
        guardar = 0;
    }


    //Método para inicializar las vistas
    private void inizialite() {
        btnJugar = findViewById(R.id.btnJugar);
        btnPuntuacion = findViewById(R.id.btnPuntuacion);
        btnConfiguracion = findViewById(R.id.btnConfiguracion);
    }

    //Método para insertar el OnClickListener
    private void inputClickListener() {
        btnJugar.setOnClickListener(this);
        btnPuntuacion.setOnClickListener(this);
        btnConfiguracion.setOnClickListener(this);

    }

    //Método para identificar la vista a la cual se le da click
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnJugar:
                guardar = 1;
                Intent intent = new Intent(MenuT.this,Juego.class);
                startActivity(intent);
                break;
            case R.id.btnPuntuacion:
                guardar = 0;
                Intent intent1 = new Intent(MenuT.this,Puntuacion.class);
                startActivity(intent1);
                break;
            case R.id.btnConfiguracion:
                guardar = 0;
                Intent intent2 = new Intent(MenuT.this,Configuracion.class);
                startActivity(intent2);
                break;

        }
    }
}
