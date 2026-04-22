package sonicwave.composite;


public class Audiolibro extends ContenidoAudio {

    private int capitulos;
    private String narrador;
    private String autorLiterario;

    public Audiolibro(String codigo, String titulo, int duracion,
                      String artista, String genero, String estado,
                      int capitulos, String narrador, String autorLiterario) {
        super(codigo, titulo, duracion, artista, genero, estado);
        this.capitulos = capitulos;
        this.narrador = narrador;
        this.autorLiterario = autorLiterario;
    }

    @Override
    protected String tipoContenido() {
        return "Audiolibro (" + capitulos + " cap.)";
    }

    public int getCapitulos()          { return capitulos; }
    public String getNarrador()        { return narrador; }
    public String getAutorLiterario()  { return autorLiterario; }
}
