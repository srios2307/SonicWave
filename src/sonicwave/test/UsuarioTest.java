package sonicwave.test;

import java.util.Date;

import sonicwave.modelo.Usuario;


public class UsuarioTest {

    public static void main(String[] args) {
        System.out.println("=== UsuarioTest ===");

        Usuario premium = new Usuario("CC1", "Ana", "ana@mail.com", "PREMIUM", new Date());
        Usuario gratuito = new Usuario("CC2", "Luis", "luis@mail.com", "GRATUITA", new Date());

        check("Un usuario PREMIUM es identificado como premium",
                premium.esPremium());

        check("Un usuario GRATUITA NO es premium",
                !gratuito.esPremium());
    }

    private static void check(String descripcion, boolean condicion) {
        System.out.println((condicion ? "[OK]   " : "[FAIL] ") + descripcion);
    }
}
