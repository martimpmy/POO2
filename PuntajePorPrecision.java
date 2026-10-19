// Estrategia alternativa: cuanto menos errores, mayor recompensa al ganar.
public class PuntajePorPrecision implements PoliticaPuntaje {
    @Override
    public int calcularPuntos(Partida partida) {
        if (!partida.gano()) return -5;
        int intentosRestantes = partida.getMaxErrores() - partida.getErrores();
        return 10 + 2 * intentosRestantes;
    }
}
