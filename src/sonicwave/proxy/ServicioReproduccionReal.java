package sonicwave.proxy;

import sonicwave.composite.ContenidoAudio;
import sonicwave.decorator.ReproductorBase;
import sonicwave.decorator.Reproducible;
import sonicwave.modelo.Usuario;


public class ServicioReproduccionReal implements ServicioReproduccion {

    @Override
    public void reproducirContenido(Usuario usuario, ContenidoAudio contenido) {
        System.out.println(">> Iniciando reproducción real para "
                + usuario.getNombre() + ": " + contenido.getTitulo());

        Reproducible reproductor = new ReproductorBase(contenido);
        reproductor.reproducir();


        usuario.sumarMinutos(contenido.getDuracion() / 60);
    }
}
