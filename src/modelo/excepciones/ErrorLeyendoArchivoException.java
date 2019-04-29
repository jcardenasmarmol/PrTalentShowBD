package modelo.excepciones;

/**
 * Representa una excepcion causada por un error al
 * leer un archivo
 * Created by Jesus on 16/04/2019.
 */
public class ErrorLeyendoArchivoException extends Exception {
    String nombreArchivo;
    String mensaje;
    Exception exception;

    public ErrorLeyendoArchivoException(String mensaje, Exception exception, String nombreArchivo){
        this.mensaje = mensaje;
        this.exception = exception;
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }

    @Override
    public void printStackTrace() {
        this.exception.printStackTrace();
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }
}
