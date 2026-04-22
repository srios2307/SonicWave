package sonicwave.proxy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import sonicwave.composite.Cancion;
import sonicwave.composite.ContenidoAudio;
import sonicwave.modelo.Usuario;

import static org.junit.jupiter.api.Assertions.*;


class ProxyReproduccionTest {

    private ByteArrayOutputStream salidaCapturada;
    private PrintStream salidaOriginal;

    private Usuario premium;
    private Usuario gratuito;
    private ContenidoAudio disponible;
    private ContenidoAudio restringido;

    @BeforeEach
    void setUp() {
        salidaOriginal = System.out;
        salidaCapturada = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salidaCapturada));

        premium = new Usuario("CC_P", "Ana", "a@mail.com", "PREMIUM", new Date());
        gratuito = new Usuario("CC_G", "Luis", "l@mail.com", "GRATUITA", new Date());

        disponible = new Cancion("C1", "CancionLibre", 200, "Artista",
                "Pop", "DISPONIBLE", "Album", 100);
        restringido = new Cancion("C2", "CancionBloqueada", 180, "Artista",
                "Pop", "EN_REVISION_DERECHOS", "Album", 90);
    }

    @AfterEach
    void tearDown() {
        System.setOut(salidaOriginal);
    }

    private String salida() {
        return salidaCapturada.toString();
    }

    @Test
    void usuarioPremiumPuedeReproducirContenidoDisponible() {
        ServicioReproduccion proxy = new ProxyReproduccion();
        proxy.reproducirContenido(premium, disponible);

        assertTrue(salida().contains("Iniciando reproducción real"),
                "El proxy debe delegar al servicio real para un usuario PREMIUM");
        assertFalse(salida().contains("Acceso denegado"));
        assertFalse(salida().contains("anuncio publicitario"),
                "Un usuario PREMIUM NO debe ver publicidad");
    }

    @Test
    void usuarioGratuitoVePublicidadAntesDeReproducir() {
        ServicioReproduccion proxy = new ProxyReproduccion();
        proxy.reproducirContenido(gratuito, disponible);

        assertTrue(salida().contains("anuncio publicitario"),
                "El proxy debe insertar publicidad a un usuario gratuito");
        assertTrue(salida().contains("Iniciando reproducción real"),
                "Después de la publicidad, debe permitir la reproducción");
    }

    @Test
    void contenidoNoDisponibleEsBloqueadoAunParaPremium() {
        ServicioReproduccion proxy = new ProxyReproduccion();
        proxy.reproducirContenido(premium, restringido);

        assertTrue(salida().contains("no disponible"),
                "Un contenido con estado distinto a DISPONIBLE debe bloquearse");
        assertFalse(salida().contains("Iniciando reproducción real"));
    }

    @Test
    void contenidoNoDisponibleBloqueaAUsuarioGratuito() {
        ServicioReproduccion proxy = new ProxyReproduccion();
        proxy.reproducirContenido(gratuito, restringido);

        assertTrue(salida().contains("no disponible"));
    }

    @Test
    void usuarioGratuitoAlcanzaLimiteDeSaltosPorHora() {
        ServicioReproduccion proxy = new ProxyReproduccion();


        for (int i = 0; i < 7; i++) {
            proxy.reproducirContenido(gratuito, disponible);
        }

        assertTrue(salida().contains("Límite de saltos"),
                "A partir de la séptima reproducción se debe reportar " +
                "el límite de saltos del usuario gratuito");
    }

    @Test
    void usuarioPremiumNoTieneLimiteDeSaltos() {
        ServicioReproduccion proxy = new ProxyReproduccion();

        for (int i = 0; i < 15; i++) {
            proxy.reproducirContenido(premium, disponible);
        }

        assertFalse(salida().contains("Límite de saltos"),
                "Los usuarios PREMIUM no deben tener restricción de saltos");
    }

    @Test
    void servicioRealYProxyImplementanLaMismaInterfaz() {

        ServicioReproduccion proxy = new ProxyReproduccion();
        ServicioReproduccion real = new ServicioReproduccionReal();

        assertDoesNotThrow(() -> proxy.reproducirContenido(premium, disponible));
        assertDoesNotThrow(() -> real.reproducirContenido(premium, disponible));
    }
}
