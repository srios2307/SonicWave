package sonicwave.decorator;


public class ReduccionRuido extends EfectoAudioDecorador {

    public ReduccionRuido(Reproducible envuelto) {
        super(envuelto);
    }

    @Override
    public void reproducir() {
        super.reproducir();
        System.out.println("   -> Reduciendo ruido de fondo");
    }

    @Override
    public String getDescripcionAudio() {
        return super.getDescripcionAudio() + " + ReduccionRuido";
    }
}
