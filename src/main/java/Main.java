import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String horaActualStr = LocalTime.now().format(formatter);
            int hora = Integer.parseInt(horaActualStr.split(":")[0]);

            String saludo;
            if (hora >= 6 && hora < 13) {
                saludo = "Buenos d\u00EDas";
            } else if (hora >= 13 && hora < 20) {
                saludo = "Buenas tardes";
            } else {
                saludo = "Buenas noches";
            }


            String msgComando = "cmd /c msg * \"" + saludo + ", son las " + horaActualStr + ". Realizando la comprobaci\u00F3n rutinaria del disco...\"";
            ejecutarComando(msgComando);

            System.out.println("Ejecutando sfc /scannow...");
            String sfcComando = "cmd /c sfc /scannow";
            ejecutarComando(sfcComando);

            String msgEscaneo = "cmd /c msg * \"El escaneo de malware se ha realizado con \u00E9xito.\"";
            ejecutarComando(msgEscaneo);

            System.out.println("Ejecutando chkdsk C:...");
            String chkdskComando = "cmd /c chkdsk C:";
            ejecutarComando(chkdskComando);

            String msgComprobacionDisco = "cmd /c msg * \"La comprobaci\u00F3n del disco C: se ha realizado con \u00E9xito.\"";
            ejecutarComando(msgComprobacionDisco);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ejecutarComando(String comando) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", comando);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

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
