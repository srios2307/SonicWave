package sonicwave.decorator;


public class AumentoGraves extends EfectoAudioDecorador {

    public AumentoGraves(Reproducible envuelto) {
        super(envuelto);
    }

    @Override
    public void reproducir() {
        super.reproducir();
        System.out.println("   -> Aumentando graves (+6 dB)");
    }

    @Override
    public String getDescripcionAudio() {
        return super.getDescripcionAudio() + " + AumentoGraves";
    }
}
