package sonicwave.test;

import sonicwave.composite.Audiolibro;


public class AudiolibroTest {

    public static void main(String[] args) {
        System.out.println("=== AudiolibroTest ===");

        Audiolibro a = new Audiolibro("A1", "Cien años de soledad", 36000,
                "Gabriel García Márquez", "Novela", "DISPONIBLE",
                20, "Narrador", "Gabriel García Márquez");

        check("El audiolibro guarda el número de capítulos",
                a.getCapitulos() == 20);

        check("El audiolibro guarda el narrador",
                a.getNarrador().equals("Narrador"));

        check("La duración total equivale a su duración propia",
                a.getDuracionTotal() == 36000);
    }

    private static void check(String descripcion, boolean condicion) {
        System.out.println((condicion ? "[OK]   " : "[FAIL] ") + descripcion);
    }
}
