package modelo.excepciones;


/**
 * Representa una excepcion causada por error en la E/S de datos
 * Created by Jesus on 16/04/2019.
 */
public class ESException extends Exception {
    String mensaje;
    Exception exception;

    public ESException(String mensaje, Exception exception){
        this.mensaje = mensaje;
        this.exception = exception;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }

    @Override
    public void printStackTrace() {
        this.exception.printStackTrace();
    }
}
