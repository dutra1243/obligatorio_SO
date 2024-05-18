package com.obligatorio;

import java.sql.Time;

public class App {
    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        new Thread(tracker).start();
        Admin admin = new Admin();
        Puerta.init();
        String[] lineas = ManejadorArchivosGenerico
                .leerArchivo("obligatorio/src/main/java/com/obligatorio/expressions.txt");
        new Thread(admin).start();
        for (String linea : lineas) {
            int randomInt = (int) (Math.random() * 500);
            try {
                Thread.sleep(randomInt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String[] datos = linea.split(";");
            admin.recibirMensaje(Integer.parseInt(datos[0]), Double.parseDouble(datos[1]), Integer.parseInt(datos[2]),
                    Integer.parseInt(datos[3]), new Time(System.currentTimeMillis() + randomInt));
        }

    }
}
