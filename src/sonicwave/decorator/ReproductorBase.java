package sonicwave.decorator;

import sonicwave.composite.ContenidoAudio;


public class ReproductorBase implements Reproducible {

    private ContenidoAudio contenido;

    public ReproductorBase(ContenidoAudio contenido) {
        this.contenido = contenido;
    }

    @Override
    public void reproducir() {
        System.out.println("[Reproductor base] Reproduciendo: "
                + contenido.getTitulo() + " (" + contenido.getDuracion() + "s)");
    }

    @Override
    public String getDescripcionAudio() {
        return "Audio original de \"" + contenido.getTitulo() + "\"";
    }

    public ContenidoAudio getContenido() { return contenido; }
}
