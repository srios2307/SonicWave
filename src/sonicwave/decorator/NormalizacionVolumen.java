package sonicwave.decorator;


public class NormalizacionVolumen extends EfectoAudioDecorador {

    public NormalizacionVolumen(Reproducible envuelto) {
        super(envuelto);
    }

    @Override
    public void reproducir() {
        super.reproducir();
        System.out.println("   -> Normalizando volumen a -14 LUFS");
    }

    @Override
    public String getDescripcionAudio() {
        return super.getDescripcionAudio() + " + NormalizacionVolumen";
    }
}
