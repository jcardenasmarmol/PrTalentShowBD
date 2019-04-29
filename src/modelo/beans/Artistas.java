package modelo.beans;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Clase de envoltura de un contenedor de Artistas
 * @author Jesus
 * Created by Jesus on 08/04/2019.
 */
public class Artistas {
    private LinkedList<Artista> artistas;

    public Artistas(){
        this.artistas = new LinkedList<Artista>();
    }

    public void add(Artista artista) {
        this.artistas.add(artista);
    }

    public ListIterator<Artista> getListIterator(){
        return this.artistas.listIterator();
    }

    public boolean isEmpty() {
        return this.artistas.isEmpty();
    }

    public int size() {
        return this.artistas.size();
    }
}
