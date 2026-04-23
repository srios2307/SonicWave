package sonicwave.test;

import sonicwave.composite.Cancion;


public class CancionTest {

    public static void main(String[] args) {
        System.out.println("=== CancionTest ===");

        Cancion c = new Cancion("C1", "Bohemian Rhapsody", 354,
                "Queen", "Rock", "DISPONIBLE", "A Night at the Opera", 72);

        check("La canción guarda correctamente su título",
                c.getTitulo().equals("Bohemian Rhapsody"));

        check("La duración es la esperada",
                c.getDuracion() == 354);

        check("El álbum y BPM se guardan correctamente",
                c.getAlbum().equals("A Night at the Opera") && c.getBpm() == 72);
    }

    private static void check(String descripcion, boolean condicion) {
        System.out.println((condicion ? "[OK]   " : "[FAIL] ") + descripcion);
    }
}
