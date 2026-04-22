package sonicwave.factory;

import sonicwave.composite.Cancion;
import sonicwave.composite.ContenidoAudio;

public class CancionFactory extends ContenidoFactory {

    private String codigo, titulo, artista, genero, estado, album;
    private int duracion, bpm;

    public CancionFactory(String codigo, String titulo, int duracion,
                          String artista, String genero, String estado,
                          String album, int bpm) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.duracion = duracion;
        this.artista = artista;
        this.genero = genero;
        this.estado = estado;
        this.album = album;
        this.bpm = bpm;
    }

    @Override
    public ContenidoAudio crearContenido() {
        return new Cancion(codigo, titulo, duracion, artista,
                genero, estado, album, bpm);
    }
}
