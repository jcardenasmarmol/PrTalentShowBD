package vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Proporciona soporte para entrada y salida
 * @author Eduardo A. Ponce
 * @version 1.0
 */
public class UtilidadesES {
    /**
     * Flujo de entrada
     */
    private BufferedReader flujoEntrada = null;
    /**
     * Flujo de salida
     */
    private PrintStream flujoSalida = null;

    /**
     * Constructor que recibe dos parametros, un flujo de entrada y un flujo de salida
     *
     * @param flujoEntrada El flujo de entrada
     * @param flujoSalida  El flujo de salida
     */
    public UtilidadesES(BufferedReader flujoEntrada, PrintStream flujoSalida) {
        this.flujoEntrada = flujoEntrada;
        this.flujoSalida = flujoSalida;
    }

    /**
     * Muestra un mensaje por el flujo de salida
     *
     * @param cadena Mensaje que se mostrará
     */
    public void mostrar(String cadena) {
        flujoSalida.print(cadena);
    }

    /**
     * Muestra un mensaje por el flujo de salida
     * con salto de línea
     *
     * @param cadena Mensaje que se mostrará
     */
    public void mostrarln(String cadena) {
        mostrar(cadena + "\n");
    }

    /**
     * Muestra un objeto por el flujo de salida
     * con salto de línea
     *
     * @param objeto Mensaje que se mostrará
     */
    public void mostrarln(Object objeto) {
        mostrar(objeto + "\n");
    }

    /**
     * Pide una cadena desde el flujo de entrada mostrando previamente
     * un mensaje.
     *
     * @param mensaje Mensaje que se muestra
     * @return Cadena leída
     * @throws IOException Error en lectura de datos desde flujo entrada.
     */
    public String pideCadena(String mensaje) throws IOException {
        String respuesta = null;
        mostrar(mensaje);
        try {
            respuesta = flujoEntrada.readLine();
        } catch (IOException ioe) {
            throw new IOException("Error al leer de teclado en pideCadena()", ioe);
        }
        return respuesta;
    }

    /**
     * Pide un numero entero desde el flujo de entrada mostrando previamente
     * un mensaje
     *
     * @param mensaje Mensaje que se muestra
     * @return Numero entero leido
     * @throws IOException Error en lectura de datos desde el flujo de entrada
     * @throws NumberFormatException Error en el formato de numero introducido
     */
    public int leeEntero(String mensaje) throws IOException, NumberFormatException {
        int numero = 0;

        numero = Integer.parseInt(pideCadena(mensaje));

        return numero;
    }
}