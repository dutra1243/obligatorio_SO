package com.obligatorio;

import com.obligatorio.cola.ColaConPrioridad;

import java.sql.Time;
import java.util.Random;

/*
 * 
 */
public class Manual {

    private static ColaConPrioridad<Persona> colaManual = new ColaConPrioridad();

    public static void procesar(int numeroPuerta, double coincidencia, int identificadorPersona, int prioridad,
            Time tiempo) {
        Random random = new Random();
        int randomInt = random.nextInt(2);
        if (randomInt == 0) {
            Persona p = new Persona(numeroPuerta, coincidencia, identificadorPersona, prioridad, tiempo);
            colaManual.encolar(p.prioridad, p);
        }

    }

}
