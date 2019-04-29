package modelo.dao;

import modelo.beans.*;

import java.sql.*;
import java.util.Properties;

/**
 * Clase orientada a Acceso a Datos de Artistas
 * Created by Jesus on 15/04/2019.
 */
public class ArtistaDAO implements DAO {
    /**
     * Contiene el nombre de la clase Driver.
     */
    private String driver = null;

    /**
     * URL base para el acceso a la base de datos
     */
    private String urlBase = null;

    /**
     * Nombre de la base datos
     */
    private String baseDatos = null;

    /**
     * Nombre del usuario con el que se accede a la base de datos
     */
    private String usuario = null;

    /**
     * Clave del usuario
     */
    private String clave = null;

    /**
     * Representa la conexion a la base de datos
     */
    private Connection conexion = null;

    /**
     * Construye el objeto a partir de un archivo de propiedades
     * @param properties El archivo de propiedades
     */
    public ArtistaDAO(Properties properties) {
        this.driver = properties.getProperty("driver");
        this.urlBase = properties.getProperty("urlBase");
        this.baseDatos = properties.getProperty("baseDatos");
        this.usuario  =properties.getProperty("usuario");
        this.clave = properties.getProperty("clave");
    }

    /**
     * Devuelve la conexion, si no se ha establecido
     * aun, la crea previamente
     * @return La Conexion
     * @throws SQLException Error con la Base de Datos
     */
    @Override
    public Connection getConexion() throws SQLException {
        if (this.conexion == null)
            this.conexion = DriverManager.getConnection(urlBase+baseDatos, usuario, clave);
        return this.conexion;
    }

    /**
     * Cierra la conexion si se ha creado
     * @throws SQLException Error al cerrar la conexion
     */
    @Override
    public void close() throws SQLException {
        if (this.conexion != null) {
            this.conexion.close();
            this.conexion = null;
        }
    }

    /**
     * Metodo para guardar un Artista en la Base de Datos,
     * @param artista El objeto Artista para guardar
     * @throws SQLException Error con la Base de Datos
     */
    @Override
    public void insertarArtista(Artista artista) throws SQLException {
        Statement st = null;
        String datosInsert = " (nombre, edad, tipo) values ('"+artista.getNombre()+"', '"+artista.getEdad()+"', '"+artista.getTIPO()+"');";
        try {
            st = this.getConexion().createStatement();
            if (artista instanceof Cantante)
                st.execute("insert into " + baseDatos + ".cantante" + datosInsert);
            if (artista instanceof Poeta)
                st.execute("insert into " + baseDatos + ".poeta" + datosInsert);
            if (artista instanceof Malabarista)
                st.execute("insert into " + baseDatos + ".malabarista" + datosInsert);
        } finally {
            if (st!=null)
                st.close();
        }

    }

    /**
     * Borra todos los Artistas de la Base de Datos
     * @throws SQLException Error con la Base de Datos
     */
    @Override
    public void borrarArtistas() throws SQLException {
        Statement st = null;
        try {
            st = this.getConexion().createStatement();
            st.executeUpdate("DELETE FROM talent_show.poeta;");
            st.executeUpdate("DELETE FROM talent_show.cantante;");
            st.executeUpdate("DELETE FROM talent_show.malabarista;");
        }
        finally {
            if (st!=null)
                st.close();
        }
    }

    /**
     * Actualiza en la Base de Datos la Puntuacion que ha obtenido
     * el Artista
     * @param artista El artista que se actualizara
     * @throws SQLException Error con la Base de Datos
     */
    @Override
    public void actualizarPuntuacionArtista(Artista artista) throws SQLException {
        Statement st  = null;
        try {
            st = this.getConexion().createStatement();
            if (artista instanceof Cantante)
                st.executeUpdate("update "+baseDatos+".cantate set puntuacion = "+artista.getPuntuacion()+" where id = "+artista.getId()+";");
            if (artista instanceof Poeta)
                st.executeUpdate("update "+baseDatos+".poeta set puntuacion = "+artista.getPuntuacion()+" where id = "+artista.getId()+";");
            if (artista instanceof Malabarista)
                st.executeUpdate("update "+baseDatos+".malabarista set puntuacion = "+artista.getPuntuacion()+" where id = "+artista.getId()+";");
        } finally {
            if (st!=null)
                st.close();
        }
    }

    /**
     * Realiza la consulta de Todos los Artistas almacenados en la Base de Datos
     * @return Todos los Artistas
     * @throws SQLException Error con la Base de Datos
     */
    @Override
    public Artistas consultarArtistas() throws SQLException {
        String consulta = "SELECT * FROM talent_show.poeta union SELECT * FROM talent_show.cantante union SELECT * FROM talent_show.malabarista;";
        Artistas artistas = consulta(consulta);
        return artistas;
    }

    /**
     * Realiza una consulta de los Artistas que tienen puntuacion,
     * es decir, que ya han actuado, ordenados de Mayor a Menor puntuacion
     * @return Artistas ordenados por puntuacion
     * @throws SQLException Error con la Base de Datos
     */
    @Override
    public Artistas consultaArtistasPuntuados() throws SQLException {
        String consulta = "SELECT * FROM talent_show.poeta where puntuacion !=-1 union SELECT * FROM talent_show.cantante where puntuacion !=-1 union SELECT * FROM talent_show.malabarista where puntuacion !=-1 order by puntuacion desc;";
        Artistas artistas = consulta(consulta);
        return artistas;
    }

    /**
     * Realiza una consulta de los Artistas que no tienen puntuacion,
     * es decir, que aun no han actuado
     * @return Artistas sin actuar
     * @throws SQLException Error con la Base de Datos
     */
    @Override
    public Artistas consultaArtistasNoPuntuados() throws SQLException {
        String consulta = "SELECT * FROM talent_show.poeta where puntuacion =-1 union SELECT * FROM talent_show.cantante where puntuacion =-1 union SELECT * FROM talent_show.malabarista where puntuacion =-1;";
        Artistas artistas = consulta(consulta);
        return artistas;
    }

    /**
     * Realiza una consulta a la Base de Datos
     * @param consulta Cadena con la consulta
     * @return Los Artistas que devuelva la consulta
     * @throws SQLException Error con la Base de Datos
     */
    private Artistas consulta(String consulta) throws SQLException {
        int id, edad, puntuacion;
        String nombre, tipo;
        ResultSet rs;
        Statement st = null;
        Artistas artistas = new Artistas();
        try {
            st = this.getConexion().createStatement();
            rs = st.executeQuery(consulta);
            while(rs.next()) {
                id = rs.getInt("id");
                nombre = rs.getString("nombre");
                edad = rs.getInt("edad");
                tipo = rs.getString("TIPO");
                puntuacion = rs.getInt("puntuacion");
                if (tipo.equals("P"))
                    artistas.add(new Poeta(id, nombre, edad, puntuacion));
                if (tipo.equals("C"))
                    artistas.add(new Cantante(id, nombre, edad, puntuacion));
                if (tipo.equals("M"))
                    artistas.add(new Malabarista(id, nombre, edad, puntuacion));
            }
        } finally {
            if (st!=null)
                st.close();
        }
        return artistas;
    }
}
