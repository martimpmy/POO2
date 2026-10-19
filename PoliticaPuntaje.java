// Strategy: cada implementacion decide como se puntua una ronda terminada.
public interface PoliticaPuntaje {
    int calcularPuntos(Partida partida);
}
