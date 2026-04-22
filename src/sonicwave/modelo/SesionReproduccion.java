package sonicwave.modelo;

import java.time.LocalDateTime;

import sonicwave.composite.ContenidoAudio;


public class SesionReproduccion {

    private String codigo;
    private LocalDateTime fechaHoraInicio;
    private Usuario usuario;
    private ContenidoAudio contenido;
    private int duracionEscuchada; // en segundos
    private String dispositivo;

    public SesionReproduccion(String codigo, Usuario usuario,
                              ContenidoAudio contenido, String dispositivo) {
        this.codigo = codigo;
        this.fechaHoraInicio = LocalDateTime.now();
        this.usuario = usuario;
        this.contenido = contenido;
        this.dispositivo = dispositivo;
        this.duracionEscuchada = 0;
    }

    public void registrarEscucha(int segundos) {
        this.duracionEscuchada += segundos;
    }


    public String getCodigo()                { return codigo; }
    public LocalDateTime getFechaHoraInicio(){ return fechaHoraInicio; }
    public Usuario getUsuario()              { return usuario; }
    public ContenidoAudio getContenido()     { return contenido; }
    public int getDuracionEscuchada()        { return duracionEscuchada; }
    public String getDispositivo()           { return dispositivo; }

    @Override
    public String toString() {
        return "Sesión " + codigo + " | usuario=" + usuario.getNombre()
                + " | contenido=" + contenido.getTitulo()
                + " | dispositivo=" + dispositivo
                + " | inicio=" + fechaHoraInicio;
    }
}
