package sonicwave.test;

import sonicwave.composite.Podcast;


public class PodcastTest {

    public static void main(String[] args) {
        System.out.println("=== PodcastTest ===");

        Podcast p = new Podcast("P1", "Radio Ambulante", 1800,
                "Daniel Alarcón", "Narrativo", "DISPONIBLE", 12, 4);

        check("El podcast guarda la temporada",
                p.getTemporada() == 12);

        check("El podcast guarda el episodio",
                p.getEpisodio() == 4);
    }

    private static void check(String descripcion, boolean condicion) {
        System.out.println((condicion ? "[OK]   " : "[FAIL] ") + descripcion);
    }
}
