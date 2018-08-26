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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
        inizialite();
        inputData();
    }

    private void inizialite() {
        txtCorrectas = findViewById(R.id.txtPalabrasCorrectas);
        txtIncorrectas = findViewById(R.id.txtPalabrasIncorrectas);
        btnTwi = findViewById(R.id.btnTwi);
        btnFace = findViewById(R.id.btnFace);

    }

    private void inputData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
