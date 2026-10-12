import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Partida {
    private final Palabra palabraActual;
    private final int maxErrores;
    private final Set<Character> letrasIntentadas = new HashSet<>();
    private int errores;

    public Partida(Palabra palabra) { this(palabra, 6); }

    public Partida(Palabra palabra, int maxErrores) {
        Objects.requireNonNull(palabra);
        if (maxErrores <= 0) throw new IllegalArgumentException("El límite debe ser positivo.");
        // Cada ronda posee una copia, sin el progreso de rondas anteriores.
        palabraActual = new Palabra(palabra.getPalabra(), palabra.getPista());
        this.maxErrores = maxErrores;
    }

    public void procesarLetra(char letra) {
        letra = Character.toUpperCase(letra);
        if (termino() || !letrasIntentadas.add(letra)) return;
        if (palabraActual.contieneLetra(letra)) palabraActual.agregarLetra(letra);
        else errores++;
    }
    public boolean gano() { return palabraActual.estaCompleta(); }
    public boolean perdio() { return errores >= maxErrores; }
    public boolean termino() { return gano() || perdio(); }
    public int getErrores() { return errores; }
    public int getMaxErrores() { return maxErrores; }
    public String getPista() { return palabraActual.getPista(); }
    public String getTextoPalabra() { return palabraActual.getPalabra(); }
    public String mostrarPalabra() { return palabraActual.mostrar(); }
}
