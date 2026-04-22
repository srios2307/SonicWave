package sonicwave.composite;


public class Cancion extends ContenidoAudio {

    private String album;
    private int bpm;

    public Cancion(String codigo, String titulo, int duracion,
                   String artista, String genero, String estado,
                   String album, int bpm) {
        super(codigo, titulo, duracion, artista, genero, estado);
        this.album = album;
        this.bpm = bpm;
    }

    @Override
    protected String tipoContenido() {
        return "Canción";
    }

    public String getAlbum() { return album; }
    public int getBpm()      { return bpm; }
}
