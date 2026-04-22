package sonicwave.factory;

import org.junit.jupiter.api.Test;

import sonicwave.composite.Audiolibro;
import sonicwave.composite.Cancion;
import sonicwave.composite.ContenidoAudio;
import sonicwave.composite.Podcast;

import static org.junit.jupiter.api.Assertions.*;


class ContenidoFactoryTest {

    @Test
    void cancionFactoryCreaUnaCancion() {
        ContenidoFactory f = new CancionFactory(
                "C1", "Song", 200, "Artista", "Rock", "DISPONIBLE",
                "Album", 120);

        ContenidoAudio creado = f.crearContenido();

        assertNotNull(creado);
        assertTrue(creado instanceof Cancion,
                "CancionFactory debe producir instancias de Cancion");
        assertEquals("Song", creado.getTitulo());
        assertEquals(200, creado.getDuracion());
        assertEquals(120, ((Cancion) creado).getBpm());
        assertEquals("Album", ((Cancion) creado).getAlbum());
    }

    @Test
    void podcastFactoryCreaUnPodcast() {
        ContenidoFactory f = new PodcastFactory(
                "P1", "Pod", 1500, "Host", "Narrativo", "DISPONIBLE",
                3, 7);

        ContenidoAudio creado = f.crearContenido();

        assertTrue(creado instanceof Podcast,
                "PodcastFactory debe producir instancias de Podcast");
        assertEquals(3, ((Podcast) creado).getTemporada());
        assertEquals(7, ((Podcast) creado).getEpisodio());
    }

    @Test
    void audiolibroFactoryCreaUnAudiolibro() {
        ContenidoFactory f = new AudiolibroFactory(
                "A1", "Libro", 18000, "Autor", "Novela",
                "DISPONIBLE", 15, "Narrador", "AutorLit");

        ContenidoAudio creado = f.crearContenido();

        assertTrue(creado instanceof Audiolibro,
                "AudiolibroFactory debe producir instancias de Audiolibro");
        assertEquals(15, ((Audiolibro) creado).getCapitulos());
        assertEquals("Narrador", ((Audiolibro) creado).getNarrador());
        assertEquals("AutorLit", ((Audiolibro) creado).getAutorLiterario());
    }

    @Test
    void registrarNuevoContenidoDevuelveElMismoObjetoCreado() {
        ContenidoFactory f = new CancionFactory(
                "C2", "Song2", 150, "X", "Pop", "DISPONIBLE",
                "Alb", 90);

        ContenidoAudio c = f.registrarNuevoContenido();

        assertNotNull(c);
        assertEquals("C2", c.getCodigo());
    }

    @Test
    void factoriesDistintasCreanInstanciasDiferentesDelMismoTipo() {
        ContenidoFactory f = new CancionFactory(
                "C3", "X", 100, "A", "Pop", "DISPONIBLE", "Alb", 80);

        ContenidoAudio a = f.crearContenido();
        ContenidoAudio b = f.crearContenido();

        assertNotSame(a, b,
                "Cada llamada al factory method debe producir una instancia NUEVA");
    }
}
