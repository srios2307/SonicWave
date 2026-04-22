package sonicwave.singleton;

import org.junit.jupiter.api.Test;

import java.util.Date;

import sonicwave.composite.Cancion;
import sonicwave.composite.ContenidoAudio;
import sonicwave.modelo.Usuario;

import static org.junit.jupiter.api.Assertions.*;


class ReproductorGlobalTest {

    @Test
    void getInstanceDevuelveSiempreLaMismaInstancia() {
        ReproductorGlobal a = ReproductorGlobal.getInstance();
        ReproductorGlobal b = ReproductorGlobal.getInstance();

        assertNotNull(a, "getInstance() no debe devolver null");
        assertSame(a, b, "Ambas referencias deben apuntar al MISMO objeto (Singleton)");
    }

    @Test
    void iniciarReproduccionRegistraUnaNuevaSesion() {
        ReproductorGlobal reproductor = ReproductorGlobal.getInstance();
        int sesionesAntes = reproductor.getSesionesActivas().size();

        Usuario ana = new Usuario("CC100", "Ana", "a@mail.com", "PREMIUM", new Date());
        ContenidoAudio c = new Cancion("T1", "Test", 120, "Artista",
                "Pop", "DISPONIBLE", "Album", 100);

        reproductor.iniciarReproduccion(ana, c, "Móvil");

        assertEquals(sesionesAntes + 1,
                reproductor.getSesionesActivas().size(),
                "Cada reproducción iniciada debe agregar una sesión al registro");
    }

    @Test
    void sesionesActivasNoEsNula() {
        assertNotNull(ReproductorGlobal.getInstance().getSesionesActivas());
    }
}
