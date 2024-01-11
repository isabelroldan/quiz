package org.example.model;

public class Utilities {
    /**
     * Limpia la consola de la aplicación, proporcionando una interfaz limpia y organizada.
     *
     * Utiliza el comando "cls" en sistemas Windows para limpiar la consola.
     * Si el sistema operativo no es Windows, se ignoran los comandos y se continúa.
     */
    public static void limpiarConsola() {
        try {
            // Limpia la consola de la aplicación.
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            // Capturar excepciones y mostrar la traza en caso de error.
            e.printStackTrace();
        }
    }
}
