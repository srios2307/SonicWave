package sonicwave.composite;


public abstract class ContenidoAudio implements ComponentePlaylist {

    protected String codigo;
    protected String titulo;
    protected int duracion;
    protected String artista;
    protected String genero;
    protected String estado;

    public ContenidoAudio(String codigo, String titulo, int duracion,
                          String artista, String genero, String estado) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.duracion = duracion;
        this.artista = artista;
        this.genero = genero;
        this.estado = estado;
    }

    @Override
    public double getDuracionTotal() {
        return duracion;
    }

    @Override
    public void mostrar(String indentacion) {
        System.out.println(indentacion + "- [" + tipoContenido() + "] "
                + titulo + " (" + artista + ") - "
                + duracion + "s [" + estado + "]");
    }


    protected abstract String tipoContenido();


    public String getCodigo()   { return codigo; }
    public String getTitulo()   { return titulo; }
    public int getDuracion()    { return duracion; }
    public String getArtista()  { return artista; }
    public String getGenero()   { return genero; }
    public String getEstado()   { return estado; }

    public void setEstado(String estado) { this.estado = estado; }
}
