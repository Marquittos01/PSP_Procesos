import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder();
        pb.command("ping", "-n", "3", "google.com");

        Process ps = pb.start();
        System.out.println("ID del proceso: "+ps.pid());

        // Leer la salida estándar del proceso
        BufferedReader reader = new BufferedReader(new InputStreamReader(ps.getInputStream()));
        String line;
        StringBuilder output = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        int exitCode = ps.waitFor();
        System.out.println("Salida del proceso: " + exitCode);
        System.out.println("Salida estándar:\n" + output);


        try {
            // Ruta del archivo de texto que deseas abrir
            File textFile = new File("file.txt");

            // Verifica si el escritorio es compatible y está disponible
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (textFile.exists()) {
                    desktop.open(textFile);
                    System.out.println("Archivo abierto en el editor de textos predeterminado.");
                } else {
                    System.out.println("El archivo no existe.");
                }
            } else {
                System.out.println("La operación de escritorio no está soportada en este sistema.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
