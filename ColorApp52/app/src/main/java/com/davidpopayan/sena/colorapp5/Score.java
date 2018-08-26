package com.davidpopayan.sena.colorapp5;

public class Score {
    //Declaración de variables
    private int puntaje;
    private int incorrectas;

    //Constructor vacion
    public Score() {
    }

    //Encapsulamiento para las variables de la clase Score
    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getIncorrectas() {
        return incorrectas;
    }

    public void setIncorrectas(int incorrectas) {
        this.incorrectas = incorrectas;
    }
}
