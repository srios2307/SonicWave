package sonicwave.composite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PlaylistTest {

    private Cancion cancion;
    private Podcast podcast;
    private Audiolibro audiolibro;

    @BeforeEach
    void setUp() {
        cancion = new Cancion("C1", "Bohemian", 354, "Queen",
                "Rock", "DISPONIBLE", "Opera", 72);
        podcast = new Podcast("P1", "Pod", 1800, "Host",
                "Narrativo", "DISPONIBLE", 1, 1);
        audiolibro = new Audiolibro("A1", "Libro", 36000, "Autor",
                "Novela", "DISPONIBLE", 20, "Narrador", "AutorLit");
    }

    @Test
    void duracionDeContenidoIndividualEsSuPropiaDuracion() {
        assertEquals(354.0, cancion.getDuracionTotal(), 0.001);
        assertEquals(1800.0, podcast.getDuracionTotal(), 0.001);
        assertEquals(36000.0, audiolibro.getDuracionTotal(), 0.001);
    }

    @Test
    void playlistVaciaTieneDuracionCero() {
        Playlist p = new Playlist("Vacía");
        assertEquals(0.0, p.getDuracionTotal(), 0.001);
    }

    @Test
    void playlistSumaDuracionDeSusHijosDirectos() {
        Playlist p = new Playlist("Mix");
        p.agregar(cancion);
        p.agregar(podcast);

        assertEquals(354.0 + 1800.0, p.getDuracionTotal(), 0.001);
    }

    @Test
    void playlistAnidadaSumaRecursivamenteTodosLosNiveles() {

        Playlist entrenamiento = new Playlist("Entrenamiento");
        entrenamiento.agregar(cancion);

        Playlist estudio = new Playlist("Estudio");
        estudio.agregar(podcast);
        estudio.agregar(audiolibro);

        Playlist favoritas = new Playlist("Favoritas 2026");
        favoritas.agregar(entrenamiento);
        favoritas.agregar(estudio);
        favoritas.agregar(cancion);

        double esperado = 354 + (1800 + 36000) + 354;
        assertEquals(esperado, favoritas.getDuracionTotal(), 0.001,
                "La duración debe sumar recursivamente todos los sub-playlists");
    }

    @Test
    void agregarYEliminarModificaLaDuracion() {
        Playlist p = new Playlist("Cambiante");
        p.agregar(cancion);
        p.agregar(podcast);

        double conAmbos = p.getDuracionTotal();
        p.eliminar(podcast);

        assertTrue(p.getDuracionTotal() < conAmbos);
        assertEquals(354.0, p.getDuracionTotal(), 0.001);
    }

    @Test
    void componentePlaylistTrataUniformementeHojasYCompuestos() {

        ComponentePlaylist hoja = cancion;
        ComponentePlaylist compuesto = new Playlist("X");

        assertDoesNotThrow(() -> hoja.getDuracionTotal());
        assertDoesNotThrow(() -> compuesto.getDuracionTotal());
    }
}
