public class Partida {

    private Palabra palabraActual;
    private int errores;

    private final int MAX_ERRORES = 6;

    public Partida(Palabra palabra) {
        palabraActual = palabra;
        errores = 0;
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

    public Palabra getPalabra() {
        return palabraActual;
    }

    public int getErrores() {
        return errores;
    }

    public int getMaxErrores() {
        return MAX_ERRORES;
    }
}