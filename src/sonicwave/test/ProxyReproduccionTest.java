package sonicwave.test;

import java.util.Date;

import sonicwave.composite.Cancion;
import sonicwave.composite.ContenidoAudio;
import sonicwave.modelo.Usuario;
import sonicwave.proxy.ProxyReproduccion;
import sonicwave.proxy.ServicioReproduccion;


public class ProxyReproduccionTest {

    public static void main(String[] args) {
        System.out.println("=== ProxyReproduccionTest ===");

        Usuario premium = new Usuario("CC1", "Ana", "a@mail.com",
                "PREMIUM", new Date());
        Usuario gratuito = new Usuario("CC2", "Luis", "l@mail.com",
                "GRATUITA", new Date());

        ContenidoAudio disponible = new Cancion("C1", "Libre", 200,
                "Artista", "Pop", "DISPONIBLE", "Álbum", 100);
        ContenidoAudio restringido = new Cancion("C2", "Bloqueada", 180,
                "Artista", "Pop", "EN_REVISION_DERECHOS", "Álbum", 90);

        ServicioReproduccion proxy = new ProxyReproduccion();

        System.out.println("-- Premium reproduciendo contenido disponible:");
        proxy.reproducirContenido(premium, disponible);

        System.out.println("-- Gratuito reproduciendo contenido restringido:");
        proxy.reproducirContenido(gratuito, restringido);
        check("Usuario premium está correctamente identificado",
                premium.esPremium());

        check("El contenido restringido no está marcado como DISPONIBLE",
                !restringido.getEstado().equals("DISPONIBLE"));
    }

    private static void check(String descripcion, boolean condicion) {
        System.out.println((condicion ? "[OK]   " : "[FAIL] ") + descripcion);
    }
}
