import java.util.HashSet;
import java.util.Set;

public class Palabra {

    private String palabra;
    private String pista;
    private Set<Character> letrasAdivinadas = new HashSet<>();

    public Palabra(String palabra, String pista) {
        this.palabra = palabra.toUpperCase();
        this.pista = pista;
    }

    public String getPalabra() {
        return palabra;
    }

    public String getPista() {
        return pista;
    }

    public boolean contieneLetra(char letra) {
        return palabra.contains(String.valueOf(letra));
    }

    public void agregarLetra(char letra) {
        letrasAdivinadas.add(letra);
    }

    public boolean estaCompleta() {
        for (char letra : palabra.toCharArray()) {
            if (!letrasAdivinadas.contains(letra)) {
                return false;
            }
        }

        return true;
    }

    public String mostrar() {
        StringBuilder resultado = new StringBuilder();

        for (char letra : palabra.toCharArray()) {
            if (letrasAdivinadas.contains(letra)) {
                resultado.append(letra);
            } else {
                resultado.append("_");
            }

            resultado.append(" ");
        }

        return resultado.toString();
    }
}