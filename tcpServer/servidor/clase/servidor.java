package tcp.servidor.clase;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class servidor {

    private static final String ARCHIVO_REPORTE = "ReporteEmpleado.dat";

    // Estructura para registrar entradas por empleado
    private static Map<String, String[]> registros = new HashMap<>();

    public static String getFecha() {
        Date date = new Date();
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formato.format(date);
    }

    public static String procesarRegistro(String nombre, String etapa) throws IOException {
        String timestamp = getFecha();
        String[] campos = registros.getOrDefault(nombre, new String[4]);

        switch (etapa) {
            case "ingreso":
                campos[0] = timestamp;
                break;
            case "salida_almuerzo":
                campos[1] = timestamp;
                break;
            case "ingreso_almuerzo":
                campos[2] = timestamp;
                break;
            case "salida":
                campos[3] = timestamp;
                break;
            default:
                return "Etapa desconocida.";
        }

        registros.put(nombre, campos);
        guardarRegistro(nombre, campos);
        return nombre + " -> " + etapa + " registrado a las " + timestamp;
    }

    public static void guardarRegistro(String nombre, String[] datos) throws IOException {
        FileOutputStream fos = new FileOutputStream(ARCHIVO_REPORTE, true);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

        writer.write(nombre + " | Ingreso: " + (datos[0] != null ? datos[0] : "N/A"));
        writer.write(" | Salida Almuerzo: " + (datos[1] != null ? datos[1] : "N/A"));
        writer.write(" | Ingreso Almuerzo: " + (datos[2] != null ? datos[2] : "N/A"));
        writer.write(" | Salida: " + (datos[3] != null ? datos[3] : "N/A"));
        writer.newLine();

        writer.close();
    }

    public static void procesarSolicitud(int puerto) throws Exception {
        ServerSocket servidor = new ServerSocket(puerto);
        System.out.println("Servidor de registro corriendo en el puerto " + puerto);

        while (true) {
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado");

            InputStream in = cliente.getInputStream();
            OutputStream out = cliente.getOutputStream();

            DataInputStream dis = new DataInputStream(in);
            String mensaje = dis.readUTF();
            if (mensaje.equals("x")) break;

            String[] partes = mensaje.split("\\|");
            if (partes.length != 2) {
                System.out.println("Formato inválido.");
                cliente.close();
                continue;
            }

            String nombre = partes[0].trim();
            String etapa = partes[1].trim();

            String respuesta = procesarRegistro(nombre, etapa);
            System.out.println(respuesta);

            DataOutputStream dos = new DataOutputStream(out);
            dos.writeUTF(respuesta);

            cliente.close();
        }

        servidor.close();
    }
}