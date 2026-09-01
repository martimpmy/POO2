public class Partida {

    private Palabra palabraActual;
    private int errores;
    private int puntaje;

    private final int MAX_ERRORES = 6;

    public Partida(Palabra palabra) {
        palabraActual = palabra;
        errores = 0;
        puntaje = 0;
    }

    public void procesarLetra(char letra) {
        if (palabraActual.contieneLetra(letra)) {
            palabraActual.agregarLetra(letra);
        } else {
            errores++;
        }
    }

    public boolean gano() {
        return palabraActual.estaCompleta();
    }

    public boolean perdio() {
        return errores >= MAX_ERRORES;
    }

    public void cambiarPalabra(Palabra nuevaPalabra) {
        palabraActual = nuevaPalabra;
        errores = 0;
    }

    public void actualizarPuntaje() {
        if (gano()) {
            puntaje += 10;
        } else if (perdio()) {
            puntaje = Math.max(0, puntaje - 5);
        }
    }

    public Palabra getPalabra() {
        return palabraActual;
    }

    public int getErrores() {
        return errores;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getMaxErrores() {
        return MAX_ERRORES;
    }
}