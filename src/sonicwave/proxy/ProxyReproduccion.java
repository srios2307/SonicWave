package sonicwave.proxy;

import java.util.HashMap;
import java.util.Map;

import sonicwave.composite.ContenidoAudio;
import sonicwave.modelo.Usuario;


public class ProxyReproduccion implements ServicioReproduccion {


    private static final int LIMITE_SALTOS_GRATUITO = 6;

    private ServicioReproduccionReal servicioReal;
    private Map<String, Integer> saltosRealizados = new HashMap<>();

    public ProxyReproduccion() {
        this.servicioReal = new ServicioReproduccionReal();
    }

    @Override
    public void reproducirContenido(Usuario usuario, ContenidoAudio contenido) {

        if (!verificarEstadoContenido(contenido)) {
            System.out.println("[Proxy] Contenido '" + contenido.getTitulo()
                    + "' no disponible (estado=" + contenido.getEstado() + ").");
            return;
        }

        if (!verificarAcceso(usuario, contenido)) {
            System.out.println("[Proxy] Acceso denegado a '"
                    + usuario.getNombre() + "' para reproducir '"
                    + contenido.getTitulo() + "'.");
            return;
        }

        if (!usuario.esPremium()) {
            if (!verificarLimiteSaltos(usuario)) {
                System.out.println("[Proxy] Límite de saltos por hora "
                        + "alcanzado para usuario gratuito "
                        + usuario.getNombre() + ".");
                return;
            }
            insertarPublicidad();
        }


        servicioReal.reproducirContenido(usuario, contenido);
    }


    private boolean verificarAcceso(Usuario usuario, ContenidoAudio contenido) {

        if (usuario.esPremium()) return true;


        return "DISPONIBLE".equalsIgnoreCase(contenido.getEstado());
    }

    private boolean verificarEstadoContenido(ContenidoAudio contenido) {
        return "DISPONIBLE".equalsIgnoreCase(contenido.getEstado());
    }

    private void insertarPublicidad() {
        System.out.println("[Proxy] >>> Reproduciendo anuncio publicitario (usuario gratuito) <<<");
    }

    private boolean verificarLimiteSaltos(Usuario usuario) {
        int actuales = saltosRealizados.getOrDefault(usuario.getIdentificacion(), 0);
        if (actuales >= LIMITE_SALTOS_GRATUITO) {
            return false;
        }
        saltosRealizados.put(usuario.getIdentificacion(), actuales + 1);
        return true;
    }
}
