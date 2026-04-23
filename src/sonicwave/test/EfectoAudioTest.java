package sonicwave.test;

import sonicwave.composite.Cancion;
import sonicwave.decorator.AumentoGraves;
import sonicwave.decorator.Ecualizador;
import sonicwave.decorator.ReproductorBase;
import sonicwave.decorator.Reproducible;


public class EfectoAudioTest {

    public static void main(String[] args) {
        System.out.println("=== EfectoAudioTest ===");

        Cancion c = new Cancion("C1", "Prueba", 200, "Artista",
                "Pop", "DISPONIBLE", "Álbum", 100);

        Reproducible base = new ReproductorBase(c);
        Reproducible conEfectos = new AumentoGraves(new Ecualizador(base));

        String desc = conEfectos.getDescripcionAudio();

        check("La descripción incluye 'Ecualizador'",
                desc.contains("Ecualizador"));

        check("La descripción incluye 'AumentoGraves'",
                desc.contains("AumentoGraves"));
    }

    private static void check(String descripcion, boolean condicion) {
        System.out.println((condicion ? "[OK]   " : "[FAIL] ") + descripcion);
    }
}
