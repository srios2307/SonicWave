package sonicwave.decorator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sonicwave.composite.Cancion;
import sonicwave.composite.ContenidoAudio;

import static org.junit.jupiter.api.Assertions.*;


class EfectosAudioTest {

    private ContenidoAudio contenido;
    private Reproducible base;

    @BeforeEach
    void setUp() {
        contenido = new Cancion("C1", "Canción de prueba", 200,
                "Artista", "Pop", "DISPONIBLE", "Álbum", 100);
        base = new ReproductorBase(contenido);
    }

    @Test
    void reproductorBaseDescribeElAudioOriginal() {
        String desc = base.getDescripcionAudio();
        assertTrue(desc.contains("Canción de prueba"),
                "La descripción del audio base debe incluir el título");
        assertFalse(desc.contains("+"),
                "El audio base no debe tener efectos encadenados");
    }

    @Test
    void ecualizadorAgregaSuEfectoALaDescripcion() {
        Reproducible conEQ = new Ecualizador(base);
        assertTrue(conEQ.getDescripcionAudio().contains("Ecualizador"));
    }

    @Test
    void cadenaDeEfectosAcumulaTodosLosNombres() {
        Reproducible cadena =
                new NormalizacionVolumen(
                        new ReduccionRuido(
                                new AumentoGraves(
                                        new Ecualizador(base))));

        String desc = cadena.getDescripcionAudio();

        assertAll("La descripción final debe contener los 4 efectos",
                () -> assertTrue(desc.contains("Ecualizador")),
                () -> assertTrue(desc.contains("AumentoGraves")),
                () -> assertTrue(desc.contains("ReduccionRuido")),
                () -> assertTrue(desc.contains("NormalizacionVolumen")));
    }

    @Test
    void elOrdenDeLosEfectosSeReflejaEnLaDescripcion() {

        Reproducible cadena = new AumentoGraves(new Ecualizador(base));

        String desc = cadena.getDescripcionAudio();
        int idxEQ = desc.indexOf("Ecualizador");
        int idxBass = desc.indexOf("AumentoGraves");

        assertTrue(idxEQ >= 0 && idxBass >= 0);
        assertTrue(idxEQ < idxBass,
                "El efecto más interno debe aparecer antes en la descripción");
    }

    @Test
    void decoradoresPuedenRepetirseEnLaCadena() {
        Reproducible doble = new AumentoGraves(new AumentoGraves(base));


        long apariciones = doble.getDescripcionAudio().split("AumentoGraves", -1).length - 1;
        assertEquals(2, apariciones,
                "El mismo decorador puede aplicarse varias veces sobre la cadena");
    }

    @Test
    void reproducirNoLanzaExcepcionEnCadenaLarga() {
        Reproducible cadena =
                new NormalizacionVolumen(
                        new ReduccionRuido(
                                new AumentoGraves(
                                        new Ecualizador(base))));
        assertDoesNotThrow(cadena::reproducir);
    }

    @Test
    void elReproductorBaseNoSeModificaAlDecorarlo() {

        String descBaseAntes = base.getDescripcionAudio();
        new Ecualizador(base);
        String descBaseDespues = base.getDescripcionAudio();

        assertEquals(descBaseAntes, descBaseDespues,
                "El componente base NO debe ser modificado por un decorador");
    }
}
