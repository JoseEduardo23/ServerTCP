package serial.test;

import serial.entidades.persona;
import serial.servicios.servicio;

import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main (String[] args) throws Exception{
        persona p = new persona();
        p.setNombre("José Lincango");
        p.setFechaNacimiento("23 de marzo del 2004");
        p.setDireccion("Av las sandias y psje 10");
        servicio service = new servicio();
        service.escribir(p, "C:/Users/User/Desktop/binario.data");
        System.out.println(service.leer("C:/Users/User/Desktop/binario.data"));

        persona p1 = new persona();
        p1.setNombre("Jorge");
        p1.setDireccion("Avvvvv");
        p1.setFechaNacimiento("15 de mayo del 2003");

        persona p2 = new persona();
        p2.setNombre("Dylan");
        p2.setDireccion("Avvvvv");
        p2.setFechaNacimiento("16 de marzo del 2004");

        persona p3 = new persona();
        p3.setNombre("Marlon");
        p3.setDireccion("Avvvvv");
        p3.setFechaNacimiento("2 de septiembre del 2000");

        List<persona> lista = new ArrayList<>();
        lista.add(p1);
        lista.add(p2);
        lista.add(p3);
        service.escribirlista(lista,"C:/Users/User/Desktop/binarios.txt");
        System.out.println(service.leerlista("C:/Users/User/Desktop/binarios.txt"));

    }
}
