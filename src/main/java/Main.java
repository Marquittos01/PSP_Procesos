import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Ejecutando el comando...");
            Process process = Runtime.getRuntime().exec("cmd /c date /t");
            if (process != null) {
                System.out.println("Proceso creado correctamente.");
            } else {
                System.out.println("No se pudo crear el proceso.");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                System.out.println("Error: " + errorLine);
            }
            System.out.println("Esperando que el proceso termine...");
            int exitCode = process.waitFor();
            System.out.println("Proceso completado con código de salida: " + exitCode);
            System.out.println("Resultado del proceso: ");
            System.out.println(output.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
