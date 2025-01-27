import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            // Obtener la hora actual para el mensaje
            String horaActual = LocalTime.now().toString();

            // Comando msg con el mensaje que deseas mostrar
            String msgComando = "cmd /c msg * \"Buenas tardes, son las " + horaActual + ". Realizando la comprobación rutinaria del disco...\"";
            ejecutarComando(msgComando);  // Ejecutar el primer mensaje

            // Ejecutar el comando sfc /scannow
            System.out.println("Ejecutando sfc /scannow...");
            String sfcComando = "cmd /c sfc /scannow";
            ejecutarComando(sfcComando);

            // Mostrar el mensaje de éxito del escaneo
            String msgEscaneo = "cmd /c msg * \"El escaneo de malware se ha realizado con exito.\"";
            ejecutarComando(msgEscaneo);

            // Ejecutar el comando chkdsk C:
            System.out.println("Ejecutando chkdsk C:...");
            String chkdskComando = "cmd /c chkdsk C:";
            ejecutarComando(chkdskComando);

            // Mostrar el mensaje de éxito de la comprobación del disco
            String msgComprobacionDisco = "cmd /c msg * \"La comprobacion del disco C: se ha realizado con exito.\"";
            ejecutarComando(msgComprobacionDisco);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ejecutarComando(String comando) {
        try {
            // Crear el ProcessBuilder
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", comando);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Leer la salida del proceso
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
