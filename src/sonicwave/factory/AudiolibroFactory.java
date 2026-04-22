package sonicwave.factory;

import sonicwave.composite.Audiolibro;
import sonicwave.composite.ContenidoAudio;


public class AudiolibroFactory extends ContenidoFactory {

    private String codigo, titulo, artista, genero, estado, narrador, autorLiterario;
    private int duracion, capitulos;

    public AudiolibroFactory(String codigo, String titulo, int duracion,
                             String artista, String genero, String estado,
                             int capitulos, String narrador, String autorLiterario) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.duracion = duracion;
        this.artista = artista;
        this.genero = genero;
        this.estado = estado;
        this.capitulos = capitulos;
        this.narrador = narrador;
        this.autorLiterario = autorLiterario;
    }

    @Override
    public ContenidoAudio crearContenido() {
        return new Audiolibro(codigo, titulo, duracion, artista,
                genero, estado, capitulos, narrador, autorLiterario);
    }
}
