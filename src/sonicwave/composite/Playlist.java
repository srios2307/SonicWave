package sonicwave.composite;

import java.util.ArrayList;
import java.util.List;


public class Playlist implements ComponentePlaylist {

    private String nombre;
    private List<ComponentePlaylist> componentes = new ArrayList<>();

    public Playlist(String nombre) {
        this.nombre = nombre;
    }

    public void agregar(ComponentePlaylist componente) {
        componentes.add(componente);
    }

    public void eliminar(ComponentePlaylist componente) {
        componentes.remove(componente);
    }

    @Override
    public double getDuracionTotal() {
        double total = 0;
        for (ComponentePlaylist c : componentes) {
            total += c.getDuracionTotal(); // recursión uniforme
        }
        return total;
    }

    @Override
    public void mostrar(String indentacion) {
        System.out.println(indentacion + "[Playlist: " + nombre
                + "] - duración total: " + getDuracionTotal() + "s");
        for (ComponentePlaylist c : componentes) {
            c.mostrar(indentacion + "   ");
        }
    }

    public String getNombre() { return nombre; }

    public List<ComponentePlaylist> getComponentes() { return componentes; }
}
