package modelo.procesos;

import modelo.beans.Artista;
import modelo.beans.Artistas;
import modelo.dao.ArtistaDAO;
import modelo.excepciones.DatosIncorrectosException;
import modelo.excepciones.BDException;
import modelo.excepciones.ESException;
import vista.Menu;
import vista.UtilidadesES;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ListIterator;

/**
 * Clase que realiza los procesos principales de la aplicacion
 * Created by Jesus on 15/04/2019.
 */
public class ProcesosGenerales {
    private UtilidadesES utilidadesES;
    private HashMap<String, Menu> menus;
    private ProcesoAddArtista procesoAddArtista;
    private ArtistaDAO artistaDAO;

    public ProcesosGenerales(UtilidadesES utilidadesES, HashMap<String, Menu> menus, ProcesoAddArtista procesoAddArtista, ArtistaDAO artistaDAO) {
        this.utilidadesES = utilidadesES;
        this.menus = menus;
        this.procesoAddArtista = procesoAddArtista;
        this.artistaDAO = artistaDAO;
    }

    public void ejecutarOpc(char opc) throws ESException, DatosIncorrectosException, BDException {
        switch (opc) {
            case '1': addArtista(menus.get(String.valueOf(opc)));break;
            case '2': mostrarArtistas();break;
            case '3': actuacionesArtistas();break;
            case '4': clasificacionGeneral();break;
            case '5': seleccionar3Mejores();break;
            case 'S': salir();break;
            default: opcionInvalida(); break;
        }
    }

    /**
     * Proceso de Agregar un Artista a la Base de Datos
     * @param menu El menu que debe mostrar
     * @throws ESException Error de E/S
     * @throws BDException Error con la base de datos
     * @throws DatosIncorrectosException Los datos introducidos no son correctos
     */
    private void addArtista(Menu menu) throws ESException, BDException, DatosIncorrectosException {

        char opc;
        do {
            menu.presentaMenu();
            opc = menu.leeOpcMenu("Elige el TIPO de artista").charAt(0);
            this.procesoAddArtista.ejecutarOpc(opc);
        } while (opc != menu.SALIR);

    }

    /**
     * Metodo para mostrar Todos los Artistas de la Base de Datos
     * @throws BDException Error con la base de datos
     */
    private void mostrarArtistas() throws BDException {
        Artistas artistas;
        try {
            artistas = this.artistaDAO.consultarArtistas();
            ListIterator<Artista> iterator = artistas.getListIterator();
            if (artistas.isEmpty()) {
                this.utilidadesES.mostrarln("No hay datos de ningun artista");
            } else {
                while (iterator.hasNext()) {
                    this.utilidadesES.mostrarln(iterator.next());
                }
            }
        } catch (SQLException e) {
            throw new BDException("Error con la Base de Datos", e);
        }

    }

    /**
     * Metodo que hace que Actuen los Artistas
     * @throws ESException Error de E/S
     * @throws BDException Error con la base de datos
     */
    private void actuacionesArtistas() throws ESException, BDException, DatosIncorrectosException {
        Artistas artistas;
        Artista artista;
        try {
            artistas = this.artistaDAO.consultaArtistasNoPuntuados();
            ListIterator<Artista> iterator = artistas.getListIterator();
            if (artistas.isEmpty()) {
                this.utilidadesES.mostrarln("No hay artistas sin actuar");
            } else {
                while (iterator.hasNext()) {
                    artista = iterator.next();
                    actuarPuntuarArtista(artista);
                }
            }
        } catch (IOException e){
            throw new ESException("Error de E/S de datos", e);
        } catch (SQLException e) {
            throw new BDException("Error con la Base de Datos", e);
        }

    }

    /**
     * Metodo para realizar la actuacion y puntuar a los artistas
     * @param artista El artista
     * @throws IOException Error de E/S
     * @throws SQLException Error con la Base de Datos
     */
    private void actuarPuntuarArtista(Artista artista) throws IOException, SQLException, DatosIncorrectosException {
        int puntuacion;
        this.utilidadesES.mostrarln(artista.saludar());
        this.utilidadesES.mostrarln(artista.actuar());
        try {
            puntuacion = this.utilidadesES.leeEntero("Puntuacion para el artista: ");
            if (puntuacion < 0 || puntuacion > 10){
                this.utilidadesES.mostrarln("La puntuacion debe estar entre 0 y 10");
            } else {
                artista.setPuntuacion(puntuacion);
                this.artistaDAO.actualizarPuntuacionArtista(artista);
            }
        } catch (NumberFormatException e) {
            this.utilidadesES.mostrarln("Error al introducir la puntuacion");
        } finally{
            this.utilidadesES.mostrarln(artista.despedirse());
        }
    }

    /**
     * Metodo para Mostrar la Clasificacion General
     * @throws BDException Error con la Base de Datos
     */
    private void clasificacionGeneral() throws BDException {
        Artistas artistas;
        try {
            artistas = this.artistaDAO.consultaArtistasPuntuados();
            ListIterator<Artista> iterator = artistas.getListIterator();
            if (artistas.isEmpty()) {
                this.utilidadesES.mostrarln("No hay datos de ningun artista");
            } else {
                while (iterator.hasNext()) {
                    this.utilidadesES.mostrarln(iterator.next());
                }
            }
        } catch (SQLException e) {
            throw new BDException("Error con la Base de Datos", e);
        }

    }

    /**
     * Metodo que selecciona los 3 Mejores Artistas, si los hay
     * @throws BDException Error con la Base de Datos
     */
    private void seleccionar3Mejores() throws BDException {
        Artistas artistas;
        try {
            artistas = this.artistaDAO.consultaArtistasPuntuados();
            ListIterator<Artista> iterator = artistas.getListIterator();
            if (artistas.isEmpty()) {
                this.utilidadesES.mostrarln("No hay datos de ningun artista");
            } else {
                if (artistas.size()<3){
                    this.utilidadesES.mostrarln("No hay 3 artistas puntuados");
                } else  {
                    for (int i=0; i<3; i++) {
                        this.utilidadesES.mostrarln(iterator.next());
                    }
                }
            }
        } catch (SQLException e) {
            throw new BDException("Error con la Base de Datos", e);
        }

    }


    /**
     * Opcion SAlir de la aplicacion, realiza la desconexion
     * de la base de datos
     * @throws BDException Error al desconectar
     */
    private void salir() throws BDException {
        try {
            this.artistaDAO.close();
        } catch (SQLException e) {
            throw new BDException("Error al cerrar la conexion", e);
        }
    }

    private void opcionInvalida() {
        this.utilidadesES.mostrarln("No existe esa opcion");
    }
}
