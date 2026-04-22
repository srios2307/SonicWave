package sonicwave.factory;

import sonicwave.composite.ContenidoAudio;
import sonicwave.composite.Podcast;


public class PodcastFactory extends ContenidoFactory {

    private String codigo, titulo, artista, genero, estado;
    private int duracion, temporada, episodio;

    public PodcastFactory(String codigo, String titulo, int duracion,
                          String artista, String genero, String estado,
                          int temporada, int episodio) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.duracion = duracion;
        this.artista = artista;
        this.genero = genero;
        this.estado = estado;
        this.temporada = temporada;
        this.episodio = episodio;
    }

    @Override
    public ContenidoAudio crearContenido() {
        return new Podcast(codigo, titulo, duracion, artista,
                genero, estado, temporada, episodio);
    }
}
