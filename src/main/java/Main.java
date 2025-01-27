import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String horaActual = sdf.format(new Date());
            int hora = Integer.parseInt(horaActual.split(":")[0]);

            String saludo;
            if (hora >= 6 && hora < 13) {
                saludo = "Buenos dias";
            } else if (hora >= 13 && hora < 20) {
                saludo = "Buenas tardes";
            } else {
                saludo = "Buenas noches";
            }

            String mensaje = saludo + ", son las " + horaActual + ". Realizando la comprobacion rutinaria del disco...";

            ProcessBuilder processBuilder = new ProcessBuilder("cmd","/c","msg * "+mensaje);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            System.out.println("Mensaje enviado con privilegios elevados.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Proceso completado con código de salida: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
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
         */
    }
}
