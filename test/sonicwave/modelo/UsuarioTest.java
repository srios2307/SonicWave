package sonicwave.modelo;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


class UsuarioTest {

    @Test
    void usuarioPremiumDebeSerIdentificadoComoPremium() {
        Usuario u = new Usuario("CC1", "Ana", "ana@mail.com", "PREMIUM", new Date());
        assertTrue(u.esPremium(), "Un usuario con tipo PREMIUM debe retornar true en esPremium()");
    }

    @Test
    void usuarioGratuitoNoDebeSerPremium() {
        Usuario u = new Usuario("CC2", "Luis", "luis@mail.com", "GRATUITA", new Date());
        assertFalse(u.esPremium(), "Un usuario con tipo GRATUITA NO debe ser premium");
    }

    @Test
    void esPremiumEsInsensibleAMayusculas() {
        Usuario u = new Usuario("CC3", "Eva", "eva@mail.com", "premium", new Date());
        assertTrue(u.esPremium(), "esPremium() debe ignorar mayúsculas/minúsculas");
    }

    @Test
    void sumarMinutosAcumulaCorrectamente() {
        Usuario u = new Usuario("CC4", "Juan", "juan@mail.com", "PREMIUM", new Date());
        assertEquals(0, u.getMinutosAcumulados());

        u.sumarMinutos(10);
        u.sumarMinutos(25);

        assertEquals(35, u.getMinutosAcumulados(),
                "Los minutos acumulados deben sumarse de forma incremental");
    }

    @Test
    void playlistsInicialmenteVacias() {
        Usuario u = new Usuario("CC5", "María", "m@mail.com", "GRATUITA", new Date());
        assertNotNull(u.getPlaylists());
        assertTrue(u.getPlaylists().isEmpty());
    }
}
