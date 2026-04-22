package sonicwave.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sonicwave.composite.Playlist;


public class Usuario {

    private String identificacion;
    private String nombre;
    private String correo;
    private String tipoSuscripcion; // "GRATUITA" o "PREMIUM"
    private Date fechaRegistro;
    private int minutosAcumulados;

    private List<Playlist> playlists = new ArrayList<>();

    public Usuario(String identificacion, String nombre, String correo,
                   String tipoSuscripcion, Date fechaRegistro) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.correo = correo;
        this.tipoSuscripcion = tipoSuscripcion;
        this.fechaRegistro = fechaRegistro;
        this.minutosAcumulados = 0;
    }


    public boolean esPremium() {
        return "PREMIUM".equalsIgnoreCase(tipoSuscripcion);
    }

    public void agregarPlaylist(Playlist p) {
        playlists.add(p);
    }

    public void sumarMinutos(int minutos) {
        this.minutosAcumulados += minutos;
    }


    public String getIdentificacion()    { return identificacion; }
    public String getNombre()            { return nombre; }
    public String getCorreo()            { return correo; }
    public String getTipoSuscripcion()   { return tipoSuscripcion; }
    public Date getFechaRegistro()       { return fechaRegistro; }
    public int getMinutosAcumulados()    { return minutosAcumulados; }
    public List<Playlist> getPlaylists() { return playlists; }
}
