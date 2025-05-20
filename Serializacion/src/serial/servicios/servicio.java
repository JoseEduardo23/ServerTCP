package serial.servicios;

import serial.entidades.persona;

import java.io.*;
import java.util.List;


public class servicio {
    public void escribir (persona p, String ruta) throws Exception{
        FileOutputStream fos = new FileOutputStream(ruta);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(p);
        oos.close();
        System.out.println("Archivo creado exitosamente");
    }

    public persona leer (String ruta) throws Exception{
        FileInputStream fis = new FileInputStream(ruta);
        ObjectInputStream ois = new ObjectInputStream(fis);
        persona p = (persona)ois.readObject();
        ois.close();
        return p;
    }

    public void escribirlista (List<persona> lista, String ruta) throws Exception{
        FileOutputStream fos = new FileOutputStream(ruta);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(lista);
        oos.close();
    }
    public List<persona> leerlista (String ruta) throws Exception{
        FileInputStream fis = new FileInputStream(ruta);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<persona> lista = (List<persona>) ois.readObject();
        ois.close();
        return lista;
    }
}
