package sonicwave.test;

import sonicwave.composite.Cancion;
import sonicwave.composite.ContenidoAudio;
import sonicwave.factory.CancionFactory;
import sonicwave.factory.ContenidoFactory;


public class ContenidoFactoryTest {

    public static void main(String[] args) {
        System.out.println("=== ContenidoFactoryTest ===");

        ContenidoFactory fabrica = new CancionFactory(
                "C1", "Song", 200, "Artista", "Rock",
                "DISPONIBLE", "Album", 120);

        ContenidoAudio creado = fabrica.crearContenido();

        check("La fábrica de canciones crea instancias de Cancion",
                creado instanceof Cancion);

        check("El contenido creado tiene el título esperado",
                creado.getTitulo().equals("Song"));
    }

    private static void check(String descripcion, boolean condicion) {
        System.out.println((condicion ? "[OK]   " : "[FAIL] ") + descripcion);
    }
}
