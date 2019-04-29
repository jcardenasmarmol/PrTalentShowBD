package modelo.beans;

/**
 * Clase que representa a un Malabarista para la aplicacion
 * @author Jesus
 * Created by Jesus on 15/04/2019.
 */
public class Malabarista extends Artista {
    /**
     * Numero de Bolos, por defecto serán 3
     */
    private int nBolos;
    public Malabarista(String nombre, int edad) {
        super(nombre, edad, "M");
        this.nBolos = 3;
    }
    public Malabarista(int id, String nombre, int edad, int puntuacion) {
        super(id, nombre, edad, puntuacion, "M");
        this.nBolos = 3;
    }

    @Override
    public String actuar() {
        return ("Hago malabares con "+this.nBolos+" bolos!");
    }

    @Override
    public String toString() {
        if (puntuacion==-1)
            return ("Malabarista -> Nombre: "+this.nombre+", edad: "+this.edad);
        else
            return ("Malabarista -> Nombre: "+this.nombre+", edad: "+this.edad+" con puntuacion: "+this.puntuacion);
    }
}
