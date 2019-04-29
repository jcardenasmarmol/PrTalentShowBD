package modelo.beans;

/**
 * Clase que representa a un Cantante para la aplicacion
 * @author Jesus
 * Created by Jesus on 15/04/2019.
 */
public class Cantante extends Artista {
    public Cantante(String nombre, int edad) {
        super(nombre, edad, "C");
    }
    public Cantante(int id, String nombre, int edad, int puntuacion) {
        super(id,nombre, edad, puntuacion, "C");
    }

    @Override
    public String actuar() {
        return "Voy a cantar una cancion por defecto";
    }

    @Override
    public String toString() {
        if (puntuacion==-1)
            return ("Cantante -> Nombre: "+this.nombre+", edad: "+this.edad);
        else
            return ("Cantante -> Nombre: "+this.nombre+", edad: "+this.edad+" con puntuacion: "+this.puntuacion);
    }
}
