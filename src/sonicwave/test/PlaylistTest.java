package sonicwave.test;

import sonicwave.composite.Cancion;
import sonicwave.composite.Playlist;
import sonicwave.composite.Podcast;


public class PlaylistTest {

    public static void main(String[] args) {
        System.out.println("=== PlaylistTest ===");

        Cancion c = new Cancion("C1", "Canción", 300, "Artista",
                "Pop", "DISPONIBLE", "Álbum", 100);
        Podcast p = new Podcast("P1", "Pod", 600, "Host",
                "Narrativo", "DISPONIBLE", 1, 1);

        Playlist sub = new Playlist("Sub");
        sub.agregar(c);

        Playlist principal = new Playlist("Principal");
        principal.agregar(p);
        principal.agregar(sub);

        check("Una playlist vacía tiene duración 0",
                new Playlist("Vacía").getDuracionTotal() == 0);

        check("La duración se calcula recursivamente (sub-playlists)",
                principal.getDuracionTotal() == 600 + 300);
    }

    private static void check(String descripcion, boolean condicion) {
        System.out.println((condicion ? "[OK]   " : "[FAIL] ") + descripcion);
    }
}
