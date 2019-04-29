package modelo.beans;

/**
 * Clase que representa a un Poeta para la aplicacion
 * @author Jesus
 * Created by Jesus on 15/04/2019.
 */
public class Poeta extends Artista {
    public Poeta(String nombre, int edad) {
        super(nombre, edad, "P");
    }
    public Poeta(int id, String nombre, int edad, int puntuacion) {
        super(id, nombre, edad, puntuacion, "P");
    }

    @Override
    public String actuar() {
        return ("Voy a recitar un poema por defecto");
    }

    @Override
    public String toString() {
        if (puntuacion==-1)
            return ("Poeta -> Nombre: "+this.nombre+", edad: "+this.edad);
        else
            return ("Poeta -> Nombre: "+this.nombre+", edad: "+this.edad+" con puntuacion: "+this.puntuacion);
    }
}
