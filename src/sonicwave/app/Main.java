package sonicwave.app;

import java.util.Date;

import sonicwave.composite.ContenidoAudio;
import sonicwave.composite.Playlist;
import sonicwave.decorator.AumentoGraves;
import sonicwave.decorator.Ecualizador;
import sonicwave.decorator.NormalizacionVolumen;
import sonicwave.decorator.ReduccionRuido;
import sonicwave.decorator.ReproductorBase;
import sonicwave.decorator.Reproducible;
import sonicwave.factory.AudiolibroFactory;
import sonicwave.factory.CancionFactory;
import sonicwave.factory.ContenidoFactory;
import sonicwave.factory.PodcastFactory;
import sonicwave.modelo.Usuario;
import sonicwave.singleton.ReproductorGlobal;


public class Main {

    public static void main(String[] args) {


        ContenidoFactory fCancion = new CancionFactory(
                "C001", "Bohemian Rhapsody", 354, "Queen",
                "Rock", "DISPONIBLE", "A Night at the Opera", 72);
        ContenidoFactory fPodcast = new PodcastFactory(
                "P001", "Radio Ambulante", 1800, "Daniel Alarcón",
                "Narrativo", "DISPONIBLE", 12, 4);
        ContenidoFactory fAudiolibro = new AudiolibroFactory(
                "A001", "Cien años de soledad", 36000, "Gabriel García Márquez",
                "Literatura", "EN_REVISION_DERECHOS", 20, "Gustavo Bonfanti",
                "Gabriel García Márquez");

        ContenidoAudio cancion   = fCancion.registrarNuevoContenido();
        ContenidoAudio podcast   = fPodcast.registrarNuevoContenido();
        ContenidoAudio audiolib  = fAudiolibro.registrarNuevoContenido();


        Playlist entrenamiento = new Playlist("Entrenamiento");
        entrenamiento.agregar(cancion);

        Playlist estudio = new Playlist("Estudio");
        estudio.agregar(podcast);
        estudio.agregar(audiolib);

        Playlist favoritas = new Playlist("Favoritas 2026");
        favoritas.agregar(entrenamiento);  // sub-playlist
        favoritas.agregar(estudio);        // sub-playlist
        favoritas.agregar(cancion);        // contenido suelto

        System.out.println("\n=== ESTRUCTURA DE PLAYLIST (Composite) ===");
        favoritas.mostrar("");
        System.out.println("Duración total de 'Favoritas 2026': "
                + favoritas.getDuracionTotal() + " segundos.\n");


        Usuario ana = new Usuario("CC1001", "Ana Pérez",
                "ana@mail.com", "PREMIUM", new Date());
        Usuario luis = new Usuario("CC1002", "Luis Gómez",
                "luis@mail.com", "GRATUITA", new Date());
        ana.agregarPlaylist(favoritas);


        ReproductorGlobal reproductor = ReproductorGlobal.getInstance();

        System.out.println("=== REPRODUCCIÓN: Ana (PREMIUM) escucha canción ===");
        reproductor.iniciarReproduccion(ana, cancion, "Móvil");

        System.out.println("\n=== REPRODUCCIÓN: Luis (GRATUITA) intenta audiolibro en revisión ===");
        reproductor.iniciarReproduccion(luis, audiolib, "Web");

        System.out.println("\n=== REPRODUCCIÓN: Luis (GRATUITA) escucha canción disponible ===");
        reproductor.iniciarReproduccion(luis, cancion, "Web");


        System.out.println("\n=== REPRODUCCIÓN CON EFECTOS (Decorator) ===");
        Reproducible base = new ReproductorBase(cancion);
        Reproducible conEfectos =
                new NormalizacionVolumen(
                    new ReduccionRuido(
                        new AumentoGraves(
                            new Ecualizador(base))));

        reproductor.iniciarReproduccionConEfectos(ana, cancion, "Auto", conEfectos);


        System.out.println("\n=== PRUEBA SINGLETON ===");
        ReproductorGlobal otraRef = ReproductorGlobal.getInstance();
        System.out.println("¿Misma instancia?: " + (reproductor == otraRef));
        System.out.println("Sesiones totales registradas: "
                + reproductor.getSesionesActivas().size());
    }
}
