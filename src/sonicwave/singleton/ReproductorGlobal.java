package sonicwave.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import sonicwave.composite.ContenidoAudio;
import sonicwave.decorator.Reproducible;
import sonicwave.modelo.SesionReproduccion;
import sonicwave.modelo.Usuario;
import sonicwave.proxy.ProxyReproduccion;
import sonicwave.proxy.ServicioReproduccion;


public class ReproductorGlobal {

    private static ReproductorGlobal instance = null;

    private final List<SesionReproduccion> sesionesActivas = new ArrayList<>();
    private final ServicioReproduccion servicio;


    private ReproductorGlobal() {
        this.servicio = new ProxyReproduccion();
    }

    public static synchronized ReproductorGlobal getInstance() {
        if (instance == null) {
            instance = new ReproductorGlobal();
        }
        return instance;
    }


    public void iniciarReproduccion(Usuario usuario, ContenidoAudio contenido,
                                    String dispositivo) {
        SesionReproduccion sesion = new SesionReproduccion(
                UUID.randomUUID().toString().substring(0, 8),
                usuario, contenido, dispositivo);
        registrarSesion(sesion);

        System.out.println("------------------------------------------------");
        System.out.println(">> " + sesion);
        servicio.reproducirContenido(usuario, contenido);
    }


    public void iniciarReproduccionConEfectos(Usuario usuario,
                                              ContenidoAudio contenido,
                                              String dispositivo,
                                              Reproducible cadenaEfectos) {
        SesionReproduccion sesion = new SesionReproduccion(
                UUID.randomUUID().toString().substring(0, 8),
                usuario, contenido, dispositivo);
        registrarSesion(sesion);

        System.out.println("------------------------------------------------");
        System.out.println(">> " + sesion);

        // El proxy decide si permite la reproducción.
        servicio.reproducirContenido(usuario, contenido);

        // Si pasó el proxy, aplicamos la cadena de efectos.
        System.out.println("[ReproductorGlobal] Aplicando efectos en cadena:");
        cadenaEfectos.reproducir();
        System.out.println("[ReproductorGlobal] Descripción final del audio: "
                + cadenaEfectos.getDescripcionAudio());
    }

    public void registrarSesion(SesionReproduccion sesion) {
        sesionesActivas.add(sesion);
    }

    public List<SesionReproduccion> getSesionesActivas() {
        return sesionesActivas;
    }
}
