package sonicwave.test;

import sonicwave.singleton.ReproductorGlobal;


public class ReproductorGlobalTest {

    public static void main(String[] args) {
        System.out.println("=== ReproductorGlobalTest ===");

        ReproductorGlobal a = ReproductorGlobal.getInstance();
        ReproductorGlobal b = ReproductorGlobal.getInstance();

        check("getInstance() no devuelve null",
                a != null);

        check("getInstance() devuelve SIEMPRE la misma instancia",
                a == b);
    }

    private static void check(String descripcion, boolean condicion) {
        System.out.println((condicion ? "[OK]   " : "[FAIL] ") + descripcion);
    }
}
