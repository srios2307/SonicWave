package sonicwave.decorator;


public abstract class EfectoAudioDecorador implements Reproducible {

    protected Reproducible envuelto;

    public EfectoAudioDecorador(Reproducible envuelto) {
        this.envuelto = envuelto;
    }

    @Override
    public void reproducir() {
        envuelto.reproducir();
    }

    @Override
    public String getDescripcionAudio() {
        return envuelto.getDescripcionAudio();
    }
}
