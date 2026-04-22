package sonicwave.composite;


public class Podcast extends ContenidoAudio {

    private int temporada;
    private int episodio;

    public Podcast(String codigo, String titulo, int duracion,
                   String artista, String genero, String estado,
                   int temporada, int episodio) {
        super(codigo, titulo, duracion, artista, genero, estado);
        this.temporada = temporada;
        this.episodio = episodio;
    }

    @Override
    protected String tipoContenido() {
        return "Podcast T" + temporada + "E" + episodio;
    }

    public int getTemporada() { return temporada; }
    public int getEpisodio()  { return episodio; }
}
