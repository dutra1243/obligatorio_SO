package com.obligatorio;

import com.obligatorio.cola.ColaConPrioridad;

import java.util.concurrent.Semaphore;
import java.sql.Time;

public class Admin implements Runnable {

    private static ColaConPrioridad<Persona>[] colas = new ColaConPrioridad[12];

    public static void recibirMensaje(int numeroPuerta, double coincidencia, int identificadorPersona, int prioridad,
            Time tiempo, int prohibido) {
        if (coincidencia < 0.95)
            return;
        if (coincidencia < 0.995) {
            Manual.procesar(numeroPuerta, coincidencia, identificadorPersona, prioridad, tiempo, prohibido);
            return;
        }
        if (prohibido == 1) {
            System.out.println("La persona fue rechazada por estar prohibida.");
            return;
        }
        Persona p = new Persona(numeroPuerta, coincidencia, identificadorPersona, prioridad, tiempo, prohibido);
        colas[numeroPuerta].encolar(p.prioridad, p);
    }

    public void admministrar() {
        int i = 0;
        while (true) {
            if (!colas[i].esVacia()) {
                Semaphore lock = Puerta.getPuertas()[i];
                if (lock.tryAcquire()) {
                    Persona p = colas[i].desencolar();
                    p.start();
                }
            }
            i++;
            if (i == 12)
                i = 0;
        }
    }

    public void run() {
        admministrar();
    }

    public Admin() {
        for (int i = 0; i < 12; i++) {
            colas[i] = new ColaConPrioridad<Persona>();
        }
    }

}
