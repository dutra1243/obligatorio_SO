package com.obligatorio;

public class Tracker implements Runnable {

    public static int esperaTotal = 0;
    public static int cantidadPersonas = 0;

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
                System.out.println("El tiempo promedio de espera es de " + ((double) esperaTotal) / cantidadPersonas
                        + " segundos");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
