package tcp.cliente.test;

import tcp.cliente.clase.cliente;

import java.util.Scanner;

public class test {
    public static void main(String[] args) throws Exception{
        Scanner scaner = new Scanner(System.in);
        System.out.println("Ingrese un nombre:");
        String nombre = scaner.nextLine();
        System.out.println(cliente.enviarNombre(nombre));
    }
}
