package com.davidpopayan.sena.colorapp5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class Configuracion extends AppCompatActivity implements View.OnClickListener{
    //Declaración de variables
    RadioButton rbtnTiempo, rbtnIntentos;
    EditText txtTiempo, txtIntentos;
    ImageButton btnJugar;
    SharedPreferences juegoC;

    //Método para la creación de la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        inizialite();
        inputOnClickListener();
        inputValues();
    }


    //Método para inizializar las vistas
    private void inizialite() {
        rbtnTiempo = findViewById(R.id.rbtnTiempo);
        rbtnIntentos = findViewById(R.id.rbtnIntentos);
        txtTiempo = findViewById(R.id.txtTiempo);
        txtIntentos = findViewById(R.id.txtIntentos);
        btnJugar = findViewById(R.id.btnJugarC);
    }

    //Método para ingresar el setOnClickListener a los botones
    private void inputOnClickListener() {
        rbtnTiempo.setOnClickListener(this);
        rbtnIntentos.setOnClickListener(this);
        btnJugar.setOnClickListener(this);
    }

    //Método para cargar las preferencias del jugador
    private void inputValues() {
        juegoC = getSharedPreferences("juegoC",MODE_PRIVATE);
        int modo = juegoC.getInt("modo",1);
        int tiempo = juegoC.getInt("tiempo", 3), faltantes= juegoC.getInt("faltantes",3);

        if (modo==1){
            rbtnTiempo.setChecked(true);
            txtTiempo.setEnabled(true);
            txtIntentos.setEnabled(false);
        }else {
            rbtnIntentos.setChecked(true);
            txtTiempo.setEnabled(false);
            txtIntentos.setEnabled(true);
        }
        txtTiempo.setText(Integer.toString(tiempo));
        txtIntentos.setText(Integer.toString(faltantes));


    }

    //Métodos para escuchar el click
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rbtnTiempo:
                txtTiempo.setEnabled(true);
                txtIntentos.setEnabled(false);
                break;

            case R.id.rbtnIntentos:
                txtTiempo.setEnabled(false);
                txtIntentos.setEnabled(true);
                break;

            case R.id.btnJugarC:
                saveData();
                break;
        }
    }

    //Método para guardar preferencias e iniciar el juego configurado
    private void saveData() {
        SharedPreferences.Editor editor = juegoC.edit();
        if (rbtnTiempo.isChecked()){
            editor.putInt("modo",1);
            try{
                int tiempo = Integer.parseInt(txtTiempo.getText().toString());
                if (tiempo>0 && tiempo<11) {
                    editor.putInt("tiempo", tiempo);
                    editor.commit();
                    Intent intent = new Intent(Configuracion.this, JuegoC.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(this, "El tiempo tiene que ser mayor a 0 y menor a 11", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText(this, "No se guardará el tiempo por estar el campo vacio o " +
                        "\n por tener caracteres que no son números", Toast.LENGTH_SHORT).show();
            }

        }else {
            editor.putInt("modo",2);

            try{
                int faltantes = Integer.parseInt(txtTiempo.getText().toString());


                if (faltantes>0 && faltantes<11) {
                    editor.putInt("faltantes",faltantes);
                    editor.commit();
                    Intent intent = new Intent(Configuracion.this, JuegoC.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(this, "El número de intentos tienen que ser mayor a 0 y menor a 11", Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                Toast.makeText(this, "No se guardará los intentos  por estar el campo vacio o " +
                        "\n por tener caracteres que no son números", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
