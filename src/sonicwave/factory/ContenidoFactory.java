package sonicwave.factory;

import sonicwave.composite.ContenidoAudio;

public abstract class ContenidoFactory {


    public abstract ContenidoAudio crearContenido();

    public ContenidoAudio registrarNuevoContenido() {
        ContenidoAudio c = crearContenido();
        System.out.println("Contenido registrado en el catálogo: "
                + c.getTitulo() + " (" + c.getCodigo() + ")");
        return c;
    }
}
