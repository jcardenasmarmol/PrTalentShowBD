package modelo.dao;

import modelo.beans.Artista;
import modelo.beans.Artistas;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Interface de Acceso a Datos
 * Created by Jesus on 15/04/2019.
 */
public interface DAO {
    Connection getConexion() throws SQLException;
    void close() throws SQLException;
    void insertarArtista(Artista usuario) throws SQLException;
    void borrarArtistas() throws SQLException;
    void actualizarPuntuacionArtista(Artista artista) throws SQLException;
    Artistas consultarArtistas() throws SQLException;
    Artistas consultaArtistasPuntuados() throws SQLException;
    Artistas consultaArtistasNoPuntuados() throws SQLException;
}
