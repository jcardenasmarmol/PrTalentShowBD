package controlador;

import modelo.dao.ArtistaDAO;
import modelo.excepciones.DatosIncorrectosException;
import modelo.excepciones.BDException;
import modelo.excepciones.ESException;
import modelo.excepciones.ErrorLeyendoArchivoException;
import modelo.procesos.ProcesoAddArtista;
import modelo.procesos.ProcesosGenerales;
import vista.Menu;
import vista.OpcionesMenu;
import vista.UtilidadesES;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Clase que contiene todos los objetos necesarios para
 * el funcionamiento de la aplicacion
 * Created by Jesus on 15/04/2019.
 */
public class ApTalentShow {
    private UtilidadesES utilidadesES;
    private Menu menuPrincipal;
    private HashMap<String,Menu> subMenus;
    private ProcesosGenerales procesosGenerales;
    private ProcesoAddArtista procesoAddArtista;
    private ArtistaDAO artistaDAO;

    public ApTalentShow() {
        this.utilidadesES = new UtilidadesES(new BufferedReader(new InputStreamReader(System.in)), System.out);
    }

    public void ejecutar() {
        char opc=' ';
        try {
            conectarBD();
            inicializar();
            do {
                try {
                    this.menuPrincipal.presentaMenu();
                    opc = menuPrincipal.leeOpcMenu("Introduce una opcion").charAt(0);
                    this.procesosGenerales.ejecutarOpc(opc);
                } catch (DatosIncorrectosException e) {
                    this.utilidadesES.mostrarln(e.getMessage());
                }
            } while (opc != menuPrincipal.SALIR);
        } catch (BDException e) {
            this.utilidadesES.mostrarln(e.getMessage()+"\nCodigo error: "+e.getErrorCode()+"\n"+e.getSQLState());
        } catch (ErrorLeyendoArchivoException e) {
            this.utilidadesES.mostrarln(e.getMessage()+"\nArchivo: "+e.getNombreArchivo());
        } catch (ESException e) {
            this.utilidadesES.mostrarln(e.getMessage());
        } finally {
            this.utilidadesES.mostrarln("Fin de la aplicacion");
        }
    }

    /**
     * Inicializa los menus y procesos de la Aplicacion
     */
    private void inicializar() {
        crearMenuPrincipal();
        crearSubMenusApp();
        this.procesoAddArtista = new ProcesoAddArtista(this.utilidadesES, this.artistaDAO);
        this.procesosGenerales = new ProcesosGenerales(this.utilidadesES, this.subMenus, this.procesoAddArtista, this.artistaDAO);
    }

    /**
     * Crear cualquier submenu que se necesite en cualquier momento
     * durante la ejecucion de la aplicacion
     */
    private void crearSubMenusApp() {
        OpcionesMenu opcionesAddArtista = new OpcionesMenu();
        opcionesAddArtista.add("Poeta");
        opcionesAddArtista.add("Cantante");
        opcionesAddArtista.add("Malabarista");
        Menu menuAddArtista = new Menu(opcionesAddArtista, this.utilidadesES);
        this.subMenus = new HashMap<String, Menu>();
        subMenus.put("1",menuAddArtista);
    }

    /**
     * Crea el Menu principal de la Aplicacion con sus Opciones
     */
    private void crearMenuPrincipal() {
        OpcionesMenu opcionesMenu = new OpcionesMenu();
        opcionesMenu.add("Añadir artista");
        opcionesMenu.add("Mostrar la lista de artistas");
        opcionesMenu.add("Actuación de todos los artistas");
        opcionesMenu.add("Clasificacion general");
        opcionesMenu.add("Seleccionar tres mejores");
        this.menuPrincipal = new Menu(opcionesMenu, this.utilidadesES);
    }

    /**
     * Crea la conexion a la Base de Datos
     * @throws BDException Error con la base de datos
     * @throws ErrorLeyendoArchivoException Error con un archivo
     * @throws ESException Error de E/S
     */
    private void conectarBD() throws BDException, ErrorLeyendoArchivoException, ESException {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("bd.properties"));
            this.artistaDAO = new ArtistaDAO(properties);
            this.artistaDAO.getConexion();
        } catch (FileNotFoundException e) {
            throw new ErrorLeyendoArchivoException("Error al leer archivo", e, "bd.properties");
        } catch (IOException e) {
            throw new ESException("Error de E/S", e);
        } catch (SQLException e) {
            throw new BDException("Error con la Base de Datos", e);
        }

    }
}
