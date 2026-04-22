package sonicwave.decorator;


public class Ecualizador extends EfectoAudioDecorador {

    public Ecualizador(Reproducible envuelto) {
        super(envuelto);
    }

    @Override
    public void reproducir() {
        super.reproducir();
        System.out.println("   -> Aplicando ecualizador");
    }

    @Override
    public String getDescripcionAudio() {
        return super.getDescripcionAudio() + " + Ecualizador";
    }
}
