package modelo.excepciones;

import java.sql.SQLException;

/**
 * Representa una excepcion causada por Errores
 * con una Base de Datos
 * Created by Jesus on 16/04/2019.
 */
public class BDException extends SQLException {
    String mensaje;
    SQLException sqlException;

    public BDException(String mensaje, SQLException sqlException){
        this.mensaje = mensaje;
        this.sqlException = sqlException;
    }

    @Override
    public String getMessage() {
        return this.mensaje;
    }

    @Override
    public void printStackTrace() {
        this.sqlException.printStackTrace();
    }

    @Override
    public int getErrorCode() {
        return this.sqlException.getErrorCode();
    }

    @Override
    public String getSQLState() {
        return this.sqlException.getSQLState();
    }
}
