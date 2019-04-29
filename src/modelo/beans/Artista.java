package modelo.beans;

/**
 * Clase que encapsula el concepto de un artista
 * @author Jesus
 * Created by Jesus on 08/04/2019.
 */
public abstract class Artista implements Actuar, Presentarse{
    /**
     * Identificador del Artista en una Base de Datos
     */
    protected int id;
    protected String nombre;
    protected int edad;
    /**
     * La puntuacion que recibirá el artista tras puntuar,
     * antes de actuar tendrá el valor -1
     */
    protected int puntuacion;
    /**
     * Tipo de Artista para guardarlo en una Base de Datos
     */
    public final String TIPO;

    public Artista(String nombre, int edad, String TIPO){
        this.nombre = nombre;
        this.edad = edad;
        this.puntuacion = -1;
        this.TIPO = TIPO;
}

    public Artista(int id, String nombre, int edad, int puntuacion, String TIPO) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.puntuacion = puntuacion;
        this.TIPO = TIPO;
    }

    @Override
    public String saludar() {
        return "Hola me llamo "+this.nombre;
    }

    @Override
    public String despedirse() {
        return "Gracias! \nHasta pronto!";
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public int getId() {
        return id;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getTIPO() {
        return TIPO;
    }
}
