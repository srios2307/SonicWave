package sonicwave.proxy;

import sonicwave.composite.ContenidoAudio;
import sonicwave.modelo.Usuario;


public interface ServicioReproduccion {
    void reproducirContenido(Usuario usuario, ContenidoAudio contenido);
}
