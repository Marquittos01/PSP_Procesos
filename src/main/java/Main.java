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
    }
}
