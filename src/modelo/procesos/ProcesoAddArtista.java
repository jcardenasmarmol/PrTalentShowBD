package modelo.procesos;

import modelo.beans.Artista;
import modelo.beans.Cantante;
import modelo.beans.Malabarista;
import modelo.beans.Poeta;
import modelo.dao.ArtistaDAO;
import modelo.excepciones.DatosIncorrectosException;
import modelo.excepciones.BDException;
import modelo.excepciones.ESException;
import vista.UtilidadesES;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Clase que Realiza el proceso de agregar un artista
 * Created by Jesus on 15/04/2019.
 */
public class ProcesoAddArtista {
    private UtilidadesES utilidadesES;
    private ArtistaDAO artistaDAO;

    public ProcesoAddArtista(UtilidadesES utilidadesES, ArtistaDAO artistaDAO) {
        this.utilidadesES = utilidadesES;
        this.artistaDAO = artistaDAO;
    }

    public void ejecutarOpc(char opc) throws ESException, BDException, DatosIncorrectosException {
        switch (opc) {
            case '1': poeta();break;
            case '2': cantante();break;
            case '3': malabarista();break;
            default: opcionInvalida(); break;
        }
    }

    private void opcionInvalida() {

    }

    /**
     * Agrega un Artista de TIPO Malabarista a la Base de Datos
     * @throws ESException Error de E/S
     * @throws BDException Error con la base de datos
     * @throws DatosIncorrectosException Los datos introducidos no son correctos
     */
    private void malabarista() throws ESException, BDException, DatosIncorrectosException {
        Artista artista;
        String nombre;
        int edad;
        try {
            this.utilidadesES.mostrarln("Agregar un malabarista:");
            nombre = this.utilidadesES.pideCadena("Introduce el nombre: ");
            comprobarNombre(nombre);
            edad = this.utilidadesES.leeEntero("Introduce la edad: ");
            artista = new Malabarista(nombre, edad);
            artistaDAO.insertarArtista(artista);
        } catch (IOException e) {
            throw new ESException("Error de E/S de datos!", e);
        } catch (SQLException e) {
            throw new BDException("Error con la base de datos", e);
        }  catch (NumberFormatException e) {
            throw new DatosIncorrectosException("Error al introducir la edad");
        }
    }

    /**
     * Agrega un Artista de TIPO Cantante a la Base de Datos
     * @throws ESException Error de E/S
     * @throws BDException Error con la base de datos
     * @throws DatosIncorrectosException Los datos introducidos no son correctos
     */
    private void cantante() throws ESException, BDException, DatosIncorrectosException {
        Artista artista;
        String nombre;
        int edad;
        try {
            this.utilidadesES.mostrarln("Agregar un cantante:");
            nombre = this.utilidadesES.pideCadena("Introduce el nombre: ");
            comprobarNombre(nombre);
            edad = this.utilidadesES.leeEntero("Introduce la edad: ");
            artista = new Cantante(nombre, edad);
            artistaDAO.insertarArtista(artista);
        } catch (IOException e) {
            throw new ESException("Error de E/S de datos!", e);
        } catch (SQLException e) {
            throw new BDException("Error con la base de datos", e);
        } catch (NumberFormatException e) {
            throw new DatosIncorrectosException("Error al introducir la edad");
        }
    }

    /**
     * Agrega un Artista de TIPO Poeta a la Base de Datos
     * @throws ESException Error de E/S
     * @throws BDException Error con la base de datos
     * @throws DatosIncorrectosException Los datos introducidos no son correctos
     */
    private void poeta() throws ESException, BDException, DatosIncorrectosException {
        Artista artista;
        String nombre;
        int edad;
        try {
            this.utilidadesES.mostrarln("Agregar un poeta:");
            nombre = this.utilidadesES.pideCadena("Introduce el nombre: ");
            comprobarNombre(nombre);
            edad = this.utilidadesES.leeEntero("Introduce la edad: ");
            artista = new Poeta(nombre, edad);
            artistaDAO.insertarArtista(artista);
        } catch (IOException e) {
            throw new ESException("Error de E/S de datos!", e);
        } catch (SQLException e) {
            throw new BDException("Error con la base de datos", e);
        } catch (NumberFormatException e) {
            throw new DatosIncorrectosException("Error al introducir la edad");
        }
    }

    /**
     * Compueba que el nombre no este vacio
     * @param nombre El nombre para comprobar
     * @throws DatosIncorrectosException El nombre esta vacio
     */
    private void comprobarNombre(String nombre) throws DatosIncorrectosException {
        if (nombre.isEmpty() || nombre.indexOf(" ")==0)
            throw new DatosIncorrectosException("El nombre no puede estar vacio");
    }
}
