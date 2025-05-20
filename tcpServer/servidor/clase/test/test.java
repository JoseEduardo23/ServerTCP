package tcp.servidor.clase.test;

import tcp.servidor.clase.servidor;

public class test {
    public static void main(String[] args) throws Exception {
        servidor servicio = new servidor();
        servicio.procesarSolicitud(5000);
    }
}
