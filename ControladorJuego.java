import java.util.Objects;

// Coordina el juego sin depender de Swing ni de archivos.
public class ControladorJuego {
    private final Usuario usuario;
    private final FuentePalabras fuente;
    private PoliticaPuntaje politica;
    private PoliticaPuntaje politicaRonda;
    private final int maxErrores;
    private Partida partida;
    private boolean puntajeAplicado;

    public ControladorJuego(Usuario usuario, FuentePalabras fuente,
            PoliticaPuntaje politica, int maxErrores) {
        this.usuario = Objects.requireNonNull(usuario);
        this.fuente = Objects.requireNonNull(fuente);
        this.politica = Objects.requireNonNull(politica);
        if (maxErrores <= 0) throw new IllegalArgumentException("El límite debe ser positivo.");
        this.maxErrores = maxErrores;
    }

    // Permite cambiar la estrategia en tiempo de ejecucion.
    // El cambio se aplica a la siguiente ronda, nunca a una ya comenzada.
    public void seleccionarPoliticaPuntaje(PoliticaPuntaje nuevaPolitica) {
        politica = Objects.requireNonNull(nuevaPolitica);
    }

    public void iniciarRonda() {
        partida = new Partida(fuente.obtenerPalabraAleatoria(), maxErrores);
        politicaRonda = politica;
        puntajeAplicado = false;
    }

    public void procesarLetra(char letra) {
        exigirRonda();
        partida.procesarLetra(letra);
        if (partida.termino() && !puntajeAplicado) {
            usuario.sumarPuntos(politicaRonda.calcularPuntos(partida));
            puntajeAplicado = true;
        }
    }

    private void exigirRonda() {
        if (partida == null) throw new IllegalStateException("Primero iniciá una ronda.");
    }
    public boolean gano() { exigirRonda(); return partida.gano(); }
    public boolean perdio() { exigirRonda(); return partida.perdio(); }
    public int getErrores() { exigirRonda(); return partida.getErrores(); }
    public int getMaxErrores() { return maxErrores; }
    public String getPista() { exigirRonda(); return partida.getPista(); }
    public String getTextoPalabra() { exigirRonda(); return partida.getTextoPalabra(); }
    public String mostrarPalabra() { exigirRonda(); return partida.mostrarPalabra(); }
    public String getNombreUsuario() { return usuario.getNombre(); }
    public int getPuntaje() { return usuario.getPuntaje(); }
}
