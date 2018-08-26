package com.davidpopayan.sena.colorapp5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Resumen extends AppCompatActivity implements View.OnClickListener{
    //Declaración de variables
    Score score = new Score();
    Button btnTwi, btnFace, btnHome;
    TextView txtCorrectas, txtIncorrectas;
    String messege = "";
    String modo="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
        inizialite();
        inputData();
    }

    //Método para inicializar las vistas
    private void inizialite() {
        txtCorrectas = findViewById(R.id.txtPalabrasCorrectas);
        txtIncorrectas = findViewById(R.id.txtPalabrasIncorrectas);
        btnTwi = findViewById(R.id.btnTwi);
        btnFace = findViewById(R.id.btnFace);
        btnHome = findViewById(R.id.btnHome);
    }

    //Método para ingresar los datos a la vista y guardar en la base de datos los resultados del juego por defecto
    private void inputData() {
        int correctas = 0;
        int incorrectas = 0;
        if (MenuT.guardar==1){
            correctas = Juego.correctas;
            incorrectas = Juego.incorrectas;
            Score score = new Score();
            score.setPuntaje(correctas);
            score.setIncorrectas(incorrectas);
            GestorDB gestorDB = new GestorDB(this);
            gestorDB.inputData(score);
            modo="Defecto";
        }else {
            correctas = JuegoC.correctas;
            incorrectas = JuegoC.incorrectas;
            modo="Configurado";

        }
        txtCorrectas.setText(Integer.toString(correctas));
        txtIncorrectas.setText(Integer.toString(incorrectas));
        messege = "ColorApp5 \n"+
                "Número de palabras correctas: "+correctas+"\n"+
                "Número de palabras incorrectas: "+incorrectas+"\n"+
                "Modo de juego: "+modo;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTwi:
                break;

            case R.id.btnFace:
                break;

            case R.id.btnHome:
                finish();
                break;
        }
    }
}
