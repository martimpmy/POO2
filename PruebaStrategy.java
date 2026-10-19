// Pruebas de la iteracion Strategy sin librerias externas.
public class PruebaStrategy {
    private static void verificar(boolean condicion, String mensaje) {
        if (!condicion) throw new AssertionError(mensaje);
    }

    public static void main(String[] args) {
        PoliticaPuntaje clasico = new PuntajeClasico();
        PoliticaPuntaje precision = new PuntajePorPrecision();

        Partida perfecta = new Partida(new Palabra("A", "Letra"), 6);
        perfecta.procesarLetra('A');
        verificar(perfecta.gano(), "La ronda deberia haberse ganado");
        verificar(clasico.calcularPuntos(perfecta) == 10, "Puntaje clasico al ganar");
        verificar(precision.calcularPuntos(perfecta) == 22, "Puntaje perfecto: 10 + 2*6");

        Partida unError = new Partida(new Palabra("A", "Letra"), 6);
        unError.procesarLetra('B');
        unError.procesarLetra('A');
        verificar(precision.calcularPuntos(unError) == 20, "Un error: 10 + 2*5");

        Partida derrota = new Partida(new Palabra("A", "Letra"), 2);
        derrota.procesarLetra('B');
        derrota.procesarLetra('C');
        verificar(derrota.perdio(), "La ronda deberia haberse perdido");
        verificar(clasico.calcularPuntos(derrota) == -5, "Puntaje clasico al perder");
        verificar(precision.calcularPuntos(derrota) == -5, "Puntaje de precision al perder");

        // La estrategia seleccionada para la ronda queda fija hasta terminarla.
        Usuario jugador = new Usuario("prueba", "clave");
        FuentePalabras fuenteFija = () -> new Palabra("A", "Letra");
        ControladorJuego controlador = new ControladorJuego(jugador, fuenteFija, clasico, 6);
        controlador.iniciarRonda();
        controlador.seleccionarPoliticaPuntaje(precision); // Cambio a mitad de ronda.
        controlador.procesarLetra('A');
        verificar(controlador.getPuntaje() == 10, "La ronda debe mantener su estrategia inicial");
        controlador.procesarLetra('A'); // No debe volver a otorgar puntos.
        verificar(controlador.getPuntaje() == 10, "No se debe puntuar dos veces");

        controlador.iniciarRonda(); // Aqui entra en vigencia la nueva estrategia.
        controlador.procesarLetra('B');
        controlador.procesarLetra('A');
        verificar(controlador.getPuntaje() == 30, "La siguiente ronda debe usar precision");

        controlador.seleccionarPoliticaPuntaje(clasico);
        controlador.iniciarRonda();
        controlador.procesarLetra('A');
        verificar(controlador.getPuntaje() == 40, "Debe poder volver a clasico");

        System.out.println("OK: todas las pruebas de Strategy pasaron.");
    }
}
