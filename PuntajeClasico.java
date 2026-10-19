// Estrategia original: premia la victoria y penaliza la derrota con puntos fijos.
public class PuntajeClasico implements PoliticaPuntaje {
    @Override
    public int calcularPuntos(Partida partida) {
        return partida.gano() ? 10 : -5;
    }
}
