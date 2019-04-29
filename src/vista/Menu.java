package vista;

import modelo.excepciones.ESException;

import java.io.IOException;

/**
 * Clase que representa un menu para la aplicacion
 * Created by Jesus on 15/04/2019.
 */
public class Menu {
    /**
     * Constante que representa la opcion de Salir
     */
    public static final char SALIR = 'S';
    private OpcionesMenu opcionesMenu;
    private UtilidadesES utilidadesES;

    public Menu(OpcionesMenu opcionesMenu, UtilidadesES utilidadesES) {
        this.opcionesMenu = opcionesMenu;
        this.utilidadesES = utilidadesES;
    }

    /**
     * Muestra las opciones del menu
     */
    public void presentaMenu() {
        int indice = 1;
        for (String opcion: opcionesMenu) {
            utilidadesES.mostrarln(indice+ " - "+opcion);
            indice++;
        }
        utilidadesES.mostrarln("S - Salir");
    }

    /**
     * Lee una opción.
     * @param texto Mensaje Que se muestra para leer el dato.
     * @return La opción introducida, como entero.
     * @throws ESException Error de E/S
     */
    public String leeOpcMenu(String texto) throws ESException {
        String opc;
        try {
            opc = utilidadesES.pideCadena(texto);
        } catch (IOException e) {
            throw new ESException("Error de E/S de datos!", e);
        }
        return opc;

    }
}
