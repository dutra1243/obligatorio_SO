package com.obligatorio;

import java.sql.Time;
import java.util.concurrent.locks.ReentrantLock;
import java.lang.Thread;

public class Persona extends Thread {
    public static ReentrantLock lock = new ReentrantLock();

    int numeroPuerta;
    double coincidencia;
    int identificadorPersona;
    int prioridad;
    Time tiempo;
    int prohibido;

    public Persona(int numeroPuerta, double coincidencia, int identificadorPersona, int prioridad, Time tiempo,
            int prohibido) {
        this.numeroPuerta = numeroPuerta;
        this.coincidencia = coincidencia;
        this.identificadorPersona = identificadorPersona;
        this.prioridad = prioridad;
        this.tiempo = tiempo;
        this.prohibido = prohibido;
    }

    public void run() {
        Puerta.AbrirPuerta(numeroPuerta);
        try {
            if (prioridad == 2)
                Thread.sleep(500); // si es discapacitado/obeso.
            else
                Thread.sleep(1500);

        } catch (Exception e) {
            e.printStackTrace();
        }
        double espera = (double) (System.currentTimeMillis() - tiempo.getTime()) / 1000;
        Tracker.esperaTotal += espera;
        Tracker.cantidadPersonas++;
        System.out.println("Persona " + identificadorPersona + " ha pasado por la puerta " + numeroPuerta + " luego de "
                + espera + " segundos de espera");
        Puerta.CerrarPuerta(numeroPuerta);
    }
}