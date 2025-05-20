package tcp.cliente.clase;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class cliente {

    private static final int PUERTO = 5000;
    private static final String IP = "192.168.56.1";

    public static String enviarNombre(String nombre) throws Exception{
        Socket cliente = new Socket(IP, PUERTO);
        InputStream in = cliente.getInputStream();
        OutputStream out = cliente.getOutputStream();

        DataOutputStream dos = new DataOutputStream(out);
        dos.writeUTF(nombre);

        DataInputStream dis = new DataInputStream(in);
        String res = dis.readUTF();
        cliente.close();
        return res;
    }


}
