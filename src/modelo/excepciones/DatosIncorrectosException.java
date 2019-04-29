package modelo.excepciones;

/**
 * Representa una excepcion causa cuando el usuario introduce mal
 * algun datos
 * Created by Jesus on 15/04/2019.
 */
public class DatosIncorrectosException extends Exception {
    String mensaje;

    public DatosIncorrectosException(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }

}
